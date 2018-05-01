# -*- encoding:utf-8 -*-
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

import BeautifulSoup
import socket
import json
import requests

# Method to parse medicine info list from webpage
# Parameter html: HTML page which includes list of medicine user typed
# Return: Parsed information from html using BeautifulSoup
# Information format [ Sequence, Medicine name, Link which leads to specific information, Cheif ingredient, Number of cheif ingredient, Company, Expert/Daily]
# Dependency: Python BeautifulSoap package
def medicine_list_info_parser(html):
    res = []
    soup = BeautifulSoup.BeautifulSoup(html)
    lines = soup.findAll('tr', attrs={'class':'s_result_list'})
    for line in lines:
        res_line = []
        for idx, item in enumerate(line):
            if type(item) != BeautifulSoup.NavigableString:
                print item.getText(),
                res_line.append(item.getText())
            if idx == 3:
                res_line.append(item.findAll('a')[0]['href'])
        print
        res.append(res_line)
    return res

# Method to parse medicine information from webpage
# Parameter html: HTML page which includes information of medicine user typed
# Return: Parsed information from html using BeautifulSoup
# Information format { medicine_name, medicine_effect, medicine_usage, medicine_notice }
# Dependency: Python BeautifulSoap package
def medicine_info_parser(html):
    res = {}
    soup = BeautifulSoup.BeautifulSoup(html)
    res['name'] = soup.findAll('div', attrs={'class': 'main_title'})[0].getText()
    res['effect'] = soup.findAll('div', attrs={'id': 'scroll_03'})[0].findAll('div', attrs={'class':'_preview __doc'})[0].findAll('div')[0].getText()
    res['usage'] = soup.findAll('div', attrs={'id': 'scroll_04'})[0].findAll('div', attrs={'class': '_preview __doc'})[0].findAll('div')[0].getText()
    res['notice'] = soup.findAll('div', attrs={'id': 'scroll_05'})[0].findAll('div', attrs={'class': '_preview'})[0].findAll('div')[0].getText()
    return res


# Receive medicine name from user and return information(list or specific information) of medicine
# Parameter: Name of medicine user wants to search
# Return: Information of medicine
def crawler(medicine_name):
    if '/' not in medicine_name:
        data = {'startCount':'0', 'requery':medicine_name, 'mode':'basic', 'sort':'RANK', 'collection':'kifda', 'item':medicine_name}
        result = requests.post("http://drug.mfds.go.kr/html/search.jsp", data)
        information = medicine_list_info_parser(result.text)
    else:
        result = requests.get("http://drug.mfds.go.kr/html"+medicine_name[1:])
        information = medicine_info_parser(result.text)['usage']
    return information

# sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# sock.bind(("192.168.0.122", 11114))
# sock.listen(5)
# try:
#     while True:
#         client, addr = sock.accept()
#         print client.recv(1024)
#         client.send("Hello")
#         client.close()
# except:
#     sock.close()

# Search list of medicine with medicine name
# crawler("타이레놀")

# Search specific information of medicine with url
# crawler("./bxsSearchDrugProduct.jsp?item_Seq=199303109")