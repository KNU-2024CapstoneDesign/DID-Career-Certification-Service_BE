package did_career_certification.config;

import did_career_certification.global.DIDRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.ens.EnsResolutionException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
public class Web3jConfig {

//    @Value("${ganache.RPC_URL}")
//    private String GANACHE_JSON_RPC_URL;
//
//    @Value("${blockchain.contract-address}")
//    private String CONTRACT_ADDRESS;

    @Value("${blockchain.issuerPrivateKey}")
    private String ISSUER_PRIVATE_KEY;

    @Value("${blockchain.holderPrivateKey}")
    private String HOLDER_PRIVATE_KEY;

    @Value("${blockchain.verifierPrivateKey}")
    private String VERIFIER_PRIVATE_KEY;


    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService("http://127.0.0.1:7545"));
    }

    @Bean(name = "issuerWallet")
    public Credentials issuerWallet() {
        return Credentials.create(ISSUER_PRIVATE_KEY);
    }

    @Bean(name = "holderWallet")
    public Credentials holderWallet() {
        return Credentials.create(HOLDER_PRIVATE_KEY);
    }

    @Bean(name = "verifierWallet")
    public Credentials verifierWallet() {
        return Credentials.create(VERIFIER_PRIVATE_KEY);
    }

    @Bean
    public ContractGasProvider contractGasProvider() {
        return new DefaultGasProvider();
    }

    @Bean
    public DIDRegistry didRegistry(Web3j web3j) {
        try {
            return DIDRegistry.load("0xF9D9D93261ca7dAffcb24E3B6D3D8f72E07c0f74", web3j, issuerWallet(),
                new DefaultGasProvider());
        } catch (EnsResolutionException e) {
            throw new IllegalStateException("Failed to resolve contract address", e);
        }
    }
}
