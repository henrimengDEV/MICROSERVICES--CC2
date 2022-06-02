package io.swagger.configuration;

import io.swagger.adapters.primary.response.PaymentResponseAdapter;
import io.swagger.adapters.secondary.cache.RedisPaymentCache;
import io.swagger.adapters.secondary.database.InMemoryPaymentRepository;
import io.swagger.application.FakePaymentService;
import io.swagger.domain.payment.PaymentCache;
import io.swagger.domain.payment.PaymentRepository;
import io.swagger.domain.payment.PaymentService;
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



    //Service
    @Bean
    public PaymentService paymentService() {
        return new FakePaymentService(paymentCache(), paymentRepository());
    }

    //Repository
    @Bean
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

    @Bean
    public PaymentCache paymentCache() {
        return new RedisPaymentCache(jedisPool);
    }

    //Adapter
    @Bean
    public PaymentResponseAdapter paymentResponseAdapter() {
        return new PaymentResponseAdapter();
    }

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}
