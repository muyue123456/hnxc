import urllib.request

response = urllib.request.urlopen('http://localhost:8080/history', timeout=10)
content = response.read().decode()

import re

items = re.findall(r'<div class="reservation-item">(.*?)</div>', content, re.DOTALL)

if items:
    print('=== 当前数据库中的预约记录 ===')
    for i, item in enumerate(items, 1):
        name = re.search(r'<h3>(.*?)</h3>', item)
        car_loc = re.search(r'<p>(.*?)</p>', item)
        status = re.search(r'<div class="status-badge[^>]*>(.*?)</div>', item)
        details = re.findall(r'<span>(.*?)</span>', item)
        
        print()
        print('预约 #%d' % i)
        if name: print('   姓名&电话: %s' % name.group(1))
        if car_loc: print('   电池&位置: %s' % car_loc.group(1))
        if status: print('   状态: %s' % status.group(1))
        if len(details) >= 2: 
            print('   %s' % details[0])
            print('   %s' % details[1])
else:
    print('数据库中暂无预约记录')