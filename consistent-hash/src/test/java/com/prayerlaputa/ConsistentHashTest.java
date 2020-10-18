package com.prayerlaputa;

import com.google.common.base.Stopwatch;
import com.prayerlaputa.consistenthash.HashAlgorithmEnum;
import com.prayerlaputa.consistenthash.KetamaConsistentHashLocator;
import com.prayerlaputa.consistenthash.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author chenglong.yu
 * created on 2020/10/20
 */
public class ConsistentHashTest {

    private Set<Node> nodeSet;
    /*
    访问统计信息
     */
    final ConcurrentHashMap<String, Long> nodeHitStatistic = new ConcurrentHashMap<String, Long>();


    @Before
    public void initData() {
        //10个节点
        nodeSet = new TreeSet<Node>();
        nodeSet.add(new Node("192.168.10.1"));
        nodeSet.add(new Node("192.168.10.2"));
        nodeSet.add(new Node("192.168.10.3"));
        nodeSet.add(new Node("192.168.10.4"));
        nodeSet.add(new Node("192.168.10.5"));
        nodeSet.add(new Node("192.168.10.6"));
        nodeSet.add(new Node("192.168.10.7"));
        nodeSet.add(new Node("192.168.10.8"));
        nodeSet.add(new Node("192.168.10.9"));
        nodeSet.add(new Node("192.168.10.10"));
    }

    @Test
    public void testDifferentVirtualNodeNum() {
        final int threadCnt = 10, loopCnt = 100000;

        for (int i = 50; i <= 260; i=i+20) {
            System.out.println();
            System.out.println("-------------");
            System.out.println("一致性hash虚拟节点数量=" + i);
            KetamaConsistentHashLocator<Node> consistentHash = new KetamaConsistentHashLocator<>(nodeSet, HashAlgorithmEnum.KETAMA_HASH, i);
            statistic(consistentHash, threadCnt, loopCnt);
        }
    }


    public void statistic(KetamaConsistentHashLocator<Node> consistentHashLocator, int threadCnt, int loopCnt) {
        nodeHitStatistic.clear();

        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("queryDataFromConsistentHash 开始模拟访问操作...");

        CountDownLatch countDownLatch = new CountDownLatch(threadCnt * loopCnt);
        for (int i = 0; i < threadCnt; i++) {
            final String name = "thread" + i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int h = 0; h < loopCnt; h++) {
                        Node node = consistentHashLocator.getNodeByKey(name + h);
                        statisticHit(node);
                        countDownLatch.countDown();
                    }
                }
            }, name);
            t.start();
        }

        try {
            countDownLatch.await();
            System.out.println("queryDataFromConsistentHash 模拟访问操作结束，耗时："+ stopwatch.stop());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printHitStatistic(threadCnt * loopCnt);
    }


    public synchronized void statisticHit(Node node) {
        Long count = nodeHitStatistic.get(node.getIp());
        if (count == null) {
            nodeHitStatistic.put(node.getIp(), 1L);
        } else {
            nodeHitStatistic.put(node.getIp(), count + 1);
        }
    }

    public void printHitStatistic(long queryTimes) {

        double tmp = 0d;
        double avgTimes = queryTimes / nodeHitStatistic.size();
        for (Map.Entry<String, Long> entry : nodeHitStatistic.entrySet()) {
            long num = entry.getValue();
            tmp += Math.pow(num - avgTimes, 2);
            System.out.println("IP:" + entry.getKey() + " hits:" + num);
        }
        double standardDeviation = Math.sqrt(tmp / nodeHitStatistic.size());
        System.out.println("总访问量=" + queryTimes + " 节点数量=" + nodeHitStatistic.size() + " 节点数据分布标准差=" + standardDeviation);

    }
}
