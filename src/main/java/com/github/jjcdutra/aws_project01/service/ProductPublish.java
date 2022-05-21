package com.github.jjcdutra.aws_project01.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jjcdutra.aws_project01.enums.EventType;
import com.github.jjcdutra.aws_project01.model.Envelope;
import com.github.jjcdutra.aws_project01.model.Product;
import com.github.jjcdutra.aws_project01.model.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductPublish {

    private static final Logger LOG = LoggerFactory.getLogger(ProductPublish.class);

    private AmazonSNS snsClient;
    @Qualifier("productEventsTopic")
    private Topic productEventsTopic;
    private ObjectMapper objectMapper;

    public ProductPublish() {
    }

    public ProductPublish(AmazonSNS snsClient, Topic productEventsTopic, ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.productEventsTopic = productEventsTopic;
        this.objectMapper = objectMapper;
    }

    public void publishProductEvent(Product product, EventType eventType, String username) {
        ProductEvent productEvent = new ProductEvent(product.getId(), product.getCode(), username);

        try {
            Envelope envelope = new Envelope(eventType, objectMapper.writeValueAsString(productEvent));

            snsClient.publish(productEventsTopic.getTopicArn(), objectMapper.writeValueAsString(envelope));
        } catch (JsonProcessingException e) {
            LOG.error("Failed to create product event message");
        }
    }
}
