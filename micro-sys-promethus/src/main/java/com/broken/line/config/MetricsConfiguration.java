package com.broken.line.config;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wanjia1
 * @date: 2023/6/12
 * <p>
 * 长文解析 https://www.cnblogs.com/throwable/p/13257557.html
 * micrometer例子2 http://wuchong.me/blog/2015/08/01/getting-started-with-metrics/
 * 参考3 https://www.cnblogs.com/dalianpai/p/13691558.html
 * 解析4 https://cloud.tencent.com/developer/article/1340347
 */
@Configuration
public class MetricsConfiguration {


    @PostConstruct
    public void setUp() {
        final List<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("my", "my"));
        Metrics.gauge("number.gauge", tags, this, MetricsConfiguration::getRandomValue);
    }

    public double getRandomValue() {
        return (double) (Math.random() * 101);
    }


}
