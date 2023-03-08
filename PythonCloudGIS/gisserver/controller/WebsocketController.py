import time
from collections import defaultdict
# 保存所有接入的用户地址
allconn = defaultdict(list)

def websocket(request):
    # 声明全局变量
    global allconn
    try:
        # 将链接(请求)存入全局字典中
        allconn[getIP(request)] = request.websocket
        if request.is_websocket():
            while True:
                message = request.websocket.wait()
                time.sleep(5)
    except:
        print('浏览器异常退出了:'+getIP(request))
        del allconn[getIP(request)]

# 发送消息
def sedClientMsg(msg):
    # 将信息发至其他所有用户的聊天框
    for i in allconn:
        allconn[i].send(str(msg).encode())

# 获取访问者IP
def getIP(request):
    if request.META.get('HTTP_X_FORWARDED_FOR'):
        ip = request.META.get("HTTP_X_FORWARDED_FOR")
    else:
        ip = request.META.get("REMOTE_ADDR")
    return ip