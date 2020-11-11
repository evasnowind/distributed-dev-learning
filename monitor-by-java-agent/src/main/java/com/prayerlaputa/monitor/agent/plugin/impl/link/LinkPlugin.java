package com.prayerlaputa.monitor.agent.plugin.impl.link;

import com.prayerlaputa.monitor.agent.config.ScanMethodConfig;
import com.prayerlaputa.monitor.agent.plugin.IPlugin;
import com.prayerlaputa.monitor.agent.plugin.InterceptPoint;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 博客：http://itstack.org
 * 论坛：http://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛获取学习源码｝
 * create by fuzhengwei on 2019
 */
public class LinkPlugin implements IPlugin {

    @Override
    public String name() {
        return "link";
    }

    @Override
    public InterceptPoint[] buildInterceptPoint() {
        return new InterceptPoint[]{
                new InterceptPoint() {
                    @Override
                    public ElementMatcher<TypeDescription> buildTypesMatcher() {
                        return ElementMatchers.nameStartsWith(ScanMethodConfig.SCAN_METHOD_NAME_START_WITH);
                    }

                    @Override
                    public ElementMatcher<MethodDescription> buildMethodsMatcher() {
                        return ElementMatchers.isMethod()
                                .and(ElementMatchers.any())
                                .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")));
                    }
                }
        };
    }

    @Override
    public Class adviceClass() {
        return LinkAdvice.class;
    }

}
