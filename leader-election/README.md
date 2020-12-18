# 说明

代码取自 https://github.com/apache/curator/tree/master/curator-examples/src/main/java/leader

说明来自：http://curator.apache.org/getting-started.html

```text
//领导者选举
LeaderSelectorListener listener = new LeaderSelectorListener()
{
 public void takeLeadership(CuratorFramework client) throws Exception {
//这个方法将会在当前节点处于Leader角色的时候调用
 } 
 public void stateChanged(CuratorFramework client, ConnectionState newState){ 
//当到ZK的连接断开的时候会回调该方法
  }
} 
LeaderSelector selector = new LeaderSelector(client, path, listener);
selector.autoRequeue(); // not required, but this is behavior that you will probably expect selector.start(); 
```