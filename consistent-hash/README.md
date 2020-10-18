# 用你熟悉的编程语言实现一致性 hash 算法  

实现参见 com.prayerlaputa.consistenthashing 下的实现 KetamaConsistentHashLocator。

## 实现说明

主要算法实现参见KetamaConsistentHashLocator，支持如下功能：  
- 给出所需节点信息，创建一致性哈希表
- 在哈希表查找节点
- 删除节点
- 添加节点

### 创建一致性哈希环
参见KetamaConsistentHashLocator构造方法。
hash算法采用了Ketama算法。

### 查询
参见KetamaConsistentHashLocator#getNodeByKey方法
基本思想
```text
            if key不在nodeMap中
                按照顺时针方向找下一个元素。
                if 没有下一个元素
                    则返回hash环中第一个元素
            else
                直接返回元素即可
```

### 删除
参见KetamaConsistentHashLocator#removeNode方法

## 测试

**测试程序参见test/java/com/prayerlaputa/ConsistentHashTest中的 testDifferentVirtualNodeNum。**

### 测试1: 计算标准差

10个节点，访问100w次，虚拟节点个数与标准差参见下面：

| 虚拟节点数量 | 标准差 |
| ------------ | ------ |
| 50           | 18597  |
| 70           | 18157  |
| 90           | 16151  |
| 110          | 10569  |
| 130          | 8523   |
| 150          | 7977   |
| 170          | 6581   |
| 190          | 4463   |
| 210          | 6241   |
| 230          | 4714   |
| 250          | 5257   |

附：测试程序输出结果

```text
-------------
一致性hash虚拟节点数量=50
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：937.8 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=18597.12277208493

-------------
一致性hash虚拟节点数量=70
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：684.4 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=18157.17706583267

-------------
一致性hash虚拟节点数量=90
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：682.9 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=16151.076645227093

-------------
一致性hash虚拟节点数量=110
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：626.0 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=10569.901986300536

-------------
一致性hash虚拟节点数量=130
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：650.4 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=8523.776862400846

-------------
一致性hash虚拟节点数量=150
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：639.2 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=7977.841199221754

-------------
一致性hash虚拟节点数量=170
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：660.4 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=6581.740256193646

-------------
一致性hash虚拟节点数量=190
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：713.6 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=4463.734445506363

-------------
一致性hash虚拟节点数量=210
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：653.0 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=6241.41557661401

-------------
一致性hash虚拟节点数量=230
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：672.7 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=4714.450084580385

-------------
一致性hash虚拟节点数量=250
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：656.2 ms
总访问量=1000000 节点数量=10 节点数据分布标准差=5257.4263095168535

```



### 测试2：给出具体分布的输出

具体分布（由于是重新运行输出，标准差与上面表格可能有些输出）

```text
-------------
一致性hash虚拟节点数量=50
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：767.0 ms
IP:192.168.10.9 hits:107551
IP:192.168.10.8 hits:83289
IP:192.168.10.10 hits:142611
IP:192.168.10.1 hits:91853
IP:192.168.10.3 hits:103608
IP:192.168.10.2 hits:108376
IP:192.168.10.5 hits:97143
IP:192.168.10.4 hits:72524
IP:192.168.10.7 hits:82926
IP:192.168.10.6 hits:110119
总访问量=1000000 节点数量=10 节点数据分布标准差=18597.12277208493

-------------
一致性hash虚拟节点数量=70
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：538.6 ms
IP:192.168.10.9 hits:94950
IP:192.168.10.8 hits:85713
IP:192.168.10.10 hits:139310
IP:192.168.10.1 hits:85499
IP:192.168.10.3 hits:110356
IP:192.168.10.2 hits:114047
IP:192.168.10.5 hits:108387
IP:192.168.10.4 hits:77896
IP:192.168.10.7 hits:79319
IP:192.168.10.6 hits:104523
总访问量=1000000 节点数量=10 节点数据分布标准差=18157.17706583267

-------------
一致性hash虚拟节点数量=90
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：673.9 ms
IP:192.168.10.9 hits:93972
IP:192.168.10.8 hits:104364
IP:192.168.10.10 hits:126689
IP:192.168.10.1 hits:95141
IP:192.168.10.3 hits:105338
IP:192.168.10.2 hits:123062
IP:192.168.10.5 hits:96002
IP:192.168.10.4 hits:75401
IP:192.168.10.7 hits:75243
IP:192.168.10.6 hits:104788
总访问量=1000000 节点数量=10 节点数据分布标准差=16151.076645227093

-------------
一致性hash虚拟节点数量=110
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：609.3 ms
IP:192.168.10.9 hits:93776
IP:192.168.10.8 hits:105862
IP:192.168.10.10 hits:118904
IP:192.168.10.1 hits:98009
IP:192.168.10.3 hits:98357
IP:192.168.10.2 hits:95528
IP:192.168.10.5 hits:108351
IP:192.168.10.4 hits:83345
IP:192.168.10.7 hits:86470
IP:192.168.10.6 hits:111398
总访问量=1000000 节点数量=10 节点数据分布标准差=10569.901986300536

-------------
一致性hash虚拟节点数量=130
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：645.0 ms
IP:192.168.10.9 hits:90278
IP:192.168.10.8 hits:107862
IP:192.168.10.10 hits:113537
IP:192.168.10.1 hits:99817
IP:192.168.10.3 hits:92200
IP:192.168.10.2 hits:101366
IP:192.168.10.5 hits:101252
IP:192.168.10.4 hits:93547
IP:192.168.10.7 hits:88217
IP:192.168.10.6 hits:111924
总访问量=1000000 节点数量=10 节点数据分布标准差=8523.776862400846

-------------
一致性hash虚拟节点数量=150
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：661.1 ms
IP:192.168.10.9 hits:100559
IP:192.168.10.8 hits:102759
IP:192.168.10.10 hits:121161
IP:192.168.10.1 hits:98050
IP:192.168.10.3 hits:97395
IP:192.168.10.2 hits:95643
IP:192.168.10.5 hits:97904
IP:192.168.10.4 hits:98383
IP:192.168.10.7 hits:87994
IP:192.168.10.6 hits:100152
总访问量=1000000 节点数量=10 节点数据分布标准差=7977.841199221754

-------------
一致性hash虚拟节点数量=170
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：641.2 ms
IP:192.168.10.9 hits:106408
IP:192.168.10.8 hits:103483
IP:192.168.10.10 hits:115778
IP:192.168.10.1 hits:94196
IP:192.168.10.3 hits:100406
IP:192.168.10.2 hits:96776
IP:192.168.10.5 hits:94383
IP:192.168.10.4 hits:95947
IP:192.168.10.7 hits:93901
IP:192.168.10.6 hits:98722
总访问量=1000000 节点数量=10 节点数据分布标准差=6581.740256193646

-------------
一致性hash虚拟节点数量=190
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：626.4 ms
IP:192.168.10.9 hits:105070
IP:192.168.10.8 hits:96645
IP:192.168.10.10 hits:107353
IP:192.168.10.1 hits:92494
IP:192.168.10.3 hits:102277
IP:192.168.10.2 hits:97792
IP:192.168.10.5 hits:97244
IP:192.168.10.4 hits:98177
IP:192.168.10.7 hits:97830
IP:192.168.10.6 hits:105118
总访问量=1000000 节点数量=10 节点数据分布标准差=4463.734445506363

-------------
一致性hash虚拟节点数量=210
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：653.5 ms
IP:192.168.10.9 hits:109246
IP:192.168.10.8 hits:97832
IP:192.168.10.10 hits:107201
IP:192.168.10.1 hits:90715
IP:192.168.10.3 hits:107984
IP:192.168.10.2 hits:98268
IP:192.168.10.5 hits:94505
IP:192.168.10.4 hits:92139
IP:192.168.10.7 hits:100644
IP:192.168.10.6 hits:101466
总访问量=1000000 节点数量=10 节点数据分布标准差=6241.41557661401

-------------
一致性hash虚拟节点数量=230
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：690.6 ms
IP:192.168.10.9 hits:104620
IP:192.168.10.8 hits:100807
IP:192.168.10.10 hits:104547
IP:192.168.10.1 hits:93609
IP:192.168.10.3 hits:101946
IP:192.168.10.2 hits:100873
IP:192.168.10.5 hits:96053
IP:192.168.10.4 hits:90753
IP:192.168.10.7 hits:105637
IP:192.168.10.6 hits:101155
总访问量=1000000 节点数量=10 节点数据分布标准差=4714.450084580385

-------------
一致性hash虚拟节点数量=250
queryDataFromConsistentHash 开始模拟访问操作...
queryDataFromConsistentHash 模拟访问操作结束，耗时：696.7 ms
IP:192.168.10.9 hits:104684
IP:192.168.10.8 hits:106713
IP:192.168.10.10 hits:99903
IP:192.168.10.1 hits:92853
IP:192.168.10.3 hits:103375
IP:192.168.10.2 hits:96821
IP:192.168.10.5 hits:96620
IP:192.168.10.4 hits:92824
IP:192.168.10.7 hits:108330
IP:192.168.10.6 hits:97877
总访问量=1000000 节点数量=10 节点数据分布标准差=5257.4263095168535
```





## 参考资料
- [一致性Hash算法](https://github.com/ssslinppp/algorithm/tree/master/ketama)
- [【并发编程】使用BlockingQueue实现<多生产者，多消费者>](http://www.cnblogs.com/ssslinppp/p/6279796.html)   
- [五分钟理解一致性哈希算法(consistent hashing)](http://blog.csdn.net/cywosp/article/details/23397179)   
- [一致性哈希算法的理解与实践](https://yikun.github.io/2016/06/09/%E4%B8%80%E8%87%B4%E6%80%A7%E5%93%88%E5%B8%8C%E7%AE%97%E6%B3%95%E7%9A%84%E7%90%86%E8%A7%A3%E4%B8%8E%E5%AE%9E%E8%B7%B5/)    
- [KetamaConsistentHash.java包括动态添加和删除node](https://gist.github.com/linux-china/7817485)
- [https://github.com/RJ/ketama](https://github.com/RJ/ketama)
- [https://github.com/bootsrc/distarch/tree/master/db/consistent-hash](https://github.com/bootsrc/distarch/tree/master/db/consistent-hash)