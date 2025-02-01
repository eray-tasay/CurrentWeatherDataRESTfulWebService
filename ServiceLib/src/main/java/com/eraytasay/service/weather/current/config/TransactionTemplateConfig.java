package com.eraytasay.service.weather.current.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class TransactionTemplateConfig {
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager)
    {
        return new TransactionTemplate(platformTransactionManager);
    }
}
