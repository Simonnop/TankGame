# 创建地图点的 python 程序

# 添加块

z=[]

for i in range(0,2):
    for j in range(6,9):
        x=[i,j]
        z.append(x)

for i in z:
    print('{'+'{},{}'.format(i[0],i[1])+'},',end='')

# 添加指定点

run=True
z=[]

while(run):
    i=int(input('input_1'))

    if(i==90):
        break

    j=int(input('input_2'))
    x=[i,j]
    z.append(x)
    print(x)

for i in z:
    print('{'+'{},{}'.format(i[0],i[1])+'},',end='')