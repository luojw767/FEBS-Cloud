package cc.mrbird.febs.tx.manager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luo
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class FebsTxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsTxManagerApplication.class, args);
    }

}
