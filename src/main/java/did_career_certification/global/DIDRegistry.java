package did_career_certification.global;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")
public class DIDRegistry extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610ac4806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80635fbc460014610046578063817bb331146100765780638809f727146100a6575b600080fd5b610060600480360381019061005b91906104e5565b6100c4565b60405161006d9190610547565b60405180910390f35b610090600480360381019061008b91906105ec565b6101de565b60405161009d91906106ab565b60405180910390f35b6100ae610344565b6040516100bb9190610547565b60405180910390f35b600080600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490506040518060200160405280848152506000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000838152602001908152602001600020600082015181600001908161017c91906108d9565b50905050600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008154809291906101d0906109da565b919050555080915050919050565b6060600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548210610261576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161025890610a6e565b60405180910390fd5b6000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600083815260200190815260200160002060000180546102be906106fc565b80601f01602080910402602001604051908101604052809291908181526020018280546102ea906106fc565b80156103375780601f1061030c57610100808354040283529160200191610337565b820191906000526020600020905b81548152906001019060200180831161031a57829003601f168201915b5050505050905092915050565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905090565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6103f2826103a9565b810181811067ffffffffffffffff82111715610411576104106103ba565b5b80604052505050565b600061042461038b565b905061043082826103e9565b919050565b600067ffffffffffffffff8211156104505761044f6103ba565b5b610459826103a9565b9050602081019050919050565b82818337600083830152505050565b600061048861048384610435565b61041a565b9050828152602081018484840111156104a4576104a36103a4565b5b6104af848285610466565b509392505050565b600082601f8301126104cc576104cb61039f565b5b81356104dc848260208601610475565b91505092915050565b6000602082840312156104fb576104fa610395565b5b600082013567ffffffffffffffff8111156105195761051861039a565b5b610525848285016104b7565b91505092915050565b6000819050919050565b6105418161052e565b82525050565b600060208201905061055c6000830184610538565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061058d82610562565b9050919050565b61059d81610582565b81146105a857600080fd5b50565b6000813590506105ba81610594565b92915050565b6105c98161052e565b81146105d457600080fd5b50565b6000813590506105e6816105c0565b92915050565b6000806040838503121561060357610602610395565b5b6000610611858286016105ab565b9250506020610622858286016105d7565b9150509250929050565b600081519050919050565b600082825260208201905092915050565b60005b8381101561066657808201518184015260208101905061064b565b60008484015250505050565b600061067d8261062c565b6106878185610637565b9350610697818560208601610648565b6106a0816103a9565b840191505092915050565b600060208201905081810360008301526106c58184610672565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061071457607f821691505b602082108103610727576107266106cd565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b60006008830261078f7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610752565b6107998683610752565b95508019841693508086168417925050509392505050565b6000819050919050565b60006107d66107d16107cc8461052e565b6107b1565b61052e565b9050919050565b6000819050919050565b6107f0836107bb565b6108046107fc826107dd565b84845461075f565b825550505050565b600090565b61081961080c565b6108248184846107e7565b505050565b5b818110156108485761083d600082610811565b60018101905061082a565b5050565b601f82111561088d5761085e8161072d565b61086784610742565b81016020851015610876578190505b61088a61088285610742565b830182610829565b50505b505050565b600082821c905092915050565b60006108b060001984600802610892565b1980831691505092915050565b60006108c9838361089f565b9150826002028217905092915050565b6108e28261062c565b67ffffffffffffffff8111156108fb576108fa6103ba565b5b61090582546106fc565b61091082828561084c565b600060209050601f8311600181146109435760008415610931578287015190505b61093b85826108bd565b8655506109a3565b601f1984166109518661072d565b60005b8281101561097957848901518255600182019150602085019450602081019050610954565b868310156109965784890151610992601f89168261089f565b8355505b6001600288020188555050505b505050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006109e58261052e565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610a1757610a166109ab565b5b600182019050919050565b7f436572746966696361746520494420646f6573206e6f74206578697374000000600082015250565b6000610a58601d83610637565b9150610a6382610a22565b602082019050919050565b60006020820190508181036000830152610a8781610a4b565b905091905056fea2646970667358221220c517d7abdec6b3b4860951821c3d887253607293828d4717a949dfbbb1b491d464736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_SETCERTIFICATE = "setCertificate";

    public static final String FUNC_GETCERTIFICATE = "getCertificate";

    public static final String FUNC_GETCERTIFICATECOUNT = "getCertificateCount";

    @Deprecated
    protected DIDRegistry(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DIDRegistry(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DIDRegistry(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DIDRegistry(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> setCertificate(String _subject) {
        final Function function = new Function(
                FUNC_SETCERTIFICATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_subject)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getCertificate(String _address, BigInteger _id) {
        final Function function = new Function(FUNC_GETCERTIFICATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address), 
                new org.web3j.abi.datatypes.generated.Uint256(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getCertificateCount() {
        final Function function = new Function(FUNC_GETCERTIFICATECOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DIDRegistry load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DIDRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DIDRegistry load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DIDRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DIDRegistry load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DIDRegistry(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DIDRegistry load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DIDRegistry(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DIDRegistry> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DIDRegistry.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DIDRegistry> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DIDRegistry.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<DIDRegistry> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DIDRegistry.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<DIDRegistry> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DIDRegistry.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}