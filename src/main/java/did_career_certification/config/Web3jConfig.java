package did_career_certification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
public class Web3jConfig {

    private final String infuraUrl;
    private final String issuerPrivateKey;
    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;

    public Web3jConfig(@Value("${infura.API_URL}") String infuraUrl,
        @Value("${blockchain.issuerPrivateKey}") String issuerPrivateKey) {
        if (issuerPrivateKey == null || issuerPrivateKey.isEmpty()) {
            throw new IllegalArgumentException("Issuer private key is missing!");
        }

        this.infuraUrl = infuraUrl;
        this.issuerPrivateKey = issuerPrivateKey;

        // Web3j instance
        this.web3j = Web3j.build(new HttpService(infuraUrl));

        // Credentials object using issuer private key
        this.credentials = Credentials.create(issuerPrivateKey);

        // Default gas provider
        this.gasProvider = new DefaultGasProvider();
    }
}
