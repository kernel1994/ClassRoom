# -*- coding: utf-8 -*-

def resolveConfig(configPath):
	d = {}

	# 读取配置文件
	with open(configPath, 'r') as fr:
		# 判断是否是第一个文件的标志
		isFirstFile = True
		fileWords = []

		for line in fr.readlines():
			# 读取包名
			if line.startswith('p:'):
				package = line.strip('\n')[2:]
			elif line.startswith(' ') or line.startswith('\t'):
				# 去除第一个空格和换行符
				# 以 - 作为分割，返回分割后的list
				words = line.strip(' \t\n').split(' ')

				# 匹配第一个权限修饰符
				if words[0] in ('pri', 'pi'):
					words[0] = 'private'
				elif words[0] == 'pub':
					words[0] = 'public'
				elif words[0] == 'pro':
					words[0] = 'protected'

				fileWords.append(words)
			else:
				if isFirstFile:
					# 因为这行需要作为文件名，所以要去除 \n
					fileName = line.strip('\n')

					d[fileName] = ''

					isFirstFile = False
				else:
					d[fileName] = fileWords[:]
					fileWords.clear()

					fileName = line.strip('\n')
					d[fileName] = ''

		d[fileName] = fileWords[:]

	return (package, d)


package, configDic = resolveConfig('j_config.cfg')
for className, attributes in configDic.items():
	with open(className + '.java', 'wt', encoding='utf-8') as fw:
		# 写入包名
		fw.write('package {0};\n'.format(package))

		# 写入 public class { 头
		fw.write('\npublic class {0} {{\n\n'.format(className.capitalize()))

		# 写入属性内容
		for words in attributes:
			# 第一个权限修饰符
			# 第二个是变量类型
			# 第三个是变量名
			fw.write('\t{0[0]} {0[1]} {0[2]};\n'.format(words))


		# 写入函数
		# 无参构造函数
		fw.write('\n\tpublic ' + className.capitalize() + '() {\n\t}\n')
		# 含参构造函数
		fw.write('\n\tpublic ' + className.capitalize() + '(')
		for i, words in enumerate(attributes):
			fw.write('{0[1]} {0[2]}'.format(words))
			if i != len(attributes) - 1:
				fw.write(', ')
		fw.write(') {\n')

		for words in attributes:
			fw.write('\t\tthis.{0[2]} = {0[2]};\n'.format(words))

		fw.write('\t}\n')


		# getter | setter
		for words in attributes:
			fw.write('\n\tpublic {0[1]} get{1}() {{\n\t\treturn {0[2]};\n\t}}\n'.format(words, words[2].capitalize()))

			fw.write('\n\tpublic void set{1}({0[1]} {0[2]}) {{\n\t\tthis.{0[2]} = {0[2]};\n\t}}\n'.format(words, words[2].capitalize()))


		# toString
		fw.write('\n\t@Override\n\tpublic String toString() {\n')
		fw.write('\t\treturn "' + className.capitalize() + ' [')
		attrNameList = [words[2] for words in attributes]
		for i, aName in enumerate(attrNameList):
			fw.write('{0}=" + {0}'.format(aName))
			if i != len(attributes) - 1:
				fw.write(' + ", ')

		fw.write(' + "]";\n\t}\n')


		# 写入最后个反括号结尾
		fw.write('\n}\n')
