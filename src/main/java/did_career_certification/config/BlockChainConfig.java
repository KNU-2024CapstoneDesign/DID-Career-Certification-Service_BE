package did_career_certification.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BlockChainConfig {

    @Value("${blockchain.issuerPrivateKey}")
    private String issuerPrivateKey;

    @Value("${blockchain.holderPrivateKey}")
    private String holderPrivateKey;

    @Value("${blockchain.verifierPrivateKey}")
    private String verifierPrivateKey;

}
