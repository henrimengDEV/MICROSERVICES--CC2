package io.paymentsAPI.configuration;

import io.paymentsAPI.adapters.secondary.cache.RedisPaymentCache;
import io.paymentsAPI.adapters.secondary.database.InMemoryPaymentRepository;
import io.paymentsAPI.application.FakePaymentService;
import io.paymentsAPI.domain.payment.PaymentCache;
import io.paymentsAPI.domain.payment.PaymentRepository;
import io.paymentsAPI.domain.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    private final JedisPool jedisPool;

    public ApplicationConfiguration(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Bean
    public PaymentCache paymentCache() {
        return new RedisPaymentCache(jedisPool);
    }

    @Bean
    public PaymentService paymentService() {
        return new FakePaymentService(paymentCache(), paymentRepository());
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}
