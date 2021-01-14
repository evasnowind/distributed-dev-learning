/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prayerlaputa.kafka.demo;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @author chenglong.yu
 */
public class KafkaSimpleDemo {

    public static void main(String[] args) {
        String servers =  "localhost:9092,localhost:9093,localhost:9094";
        String topic = "myTopic";
        String message = "test";

        KafkaProducer<String, String> producer = KafkaTool.createProducer(servers);
        KafkaTool.send(producer, topic, message);

        KafkaConsumer<String, String> consumer = KafkaTool.createConsumer(servers, topic);
        KafkaTool.readMessage(consumer, 100);

        System.out.println("kafka test finish.");
    }
}
