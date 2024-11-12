package did_career_certification.global;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
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
    public static final String BINARY = "608060405234801561001057600080fd5b50610add806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806324c483a5146100515780637471fe0314610081578063a94df14b146100b2578063c5f04d25146100ce575b600080fd5b61006b60048036038101906100669190610570565b6100fe565b6040516100789190610638565b60405180910390f35b61009b60048036038101906100969190610690565b6101ba565b6040516100a99291906106bd565b60405180910390f35b6100cc60048036038101906100c791906106f4565b61032c565b005b6100e860048036038101906100e39190610690565b610376565b6040516100f59190610638565b60405180910390f35b6000818051602081018201805184825260208301602085012081835280955050505050506000915090508060000180546101379061079b565b80601f01602080910402602001604051908101604052809291908181526020018280546101639061079b565b80156101b05780601f10610185576101008083540402835291602001916101b0565b820191906000526020600020905b81548152906001019060200180831161019357829003601f168201915b5050505050905081565b60608060006001600085815260200190815260200160002080546101dd9061079b565b80601f01602080910402602001604051908101604052809291908181526020018280546102099061079b565b80156102565780601f1061022b57610100808354040283529160200191610256565b820191906000526020600020905b81548152906001019060200180831161023957829003601f168201915b505050505090506000808260405161026e9190610808565b90815260200160405180910390206040518060200160405290816000820180546102979061079b565b80601f01602080910402602001604051908101604052809291908181526020018280546102c39061079b565b80156103105780601f106102e557610100808354040283529160200191610310565b820191906000526020600020905b8154815290600101906020018083116102f357829003601f168201915b5050505050815250509050806000015182935093505050915091565b60405180602001604052808281525060008360405161034b9190610808565b9081526020016040518091039020600082015181600001908161036e91906109d5565b509050505050565b600160205280600052604060002060009150905080546103959061079b565b80601f01602080910402602001604051908101604052809291908181526020018280546103c19061079b565b801561040e5780601f106103e35761010080835404028352916020019161040e565b820191906000526020600020905b8154815290600101906020018083116103f157829003601f168201915b505050505081565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b61047d82610434565b810181811067ffffffffffffffff8211171561049c5761049b610445565b5b80604052505050565b60006104af610416565b90506104bb8282610474565b919050565b600067ffffffffffffffff8211156104db576104da610445565b5b6104e482610434565b9050602081019050919050565b82818337600083830152505050565b600061051361050e846104c0565b6104a5565b90508281526020810184848401111561052f5761052e61042f565b5b61053a8482856104f1565b509392505050565b600082601f8301126105575761055661042a565b5b8135610567848260208601610500565b91505092915050565b60006020828403121561058657610585610420565b5b600082013567ffffffffffffffff8111156105a4576105a3610425565b5b6105b084828501610542565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b838110156105f35780820151818401526020810190506105d8565b60008484015250505050565b600061060a826105b9565b61061481856105c4565b93506106248185602086016105d5565b61062d81610434565b840191505092915050565b6000602082019050818103600083015261065281846105ff565b905092915050565b6000819050919050565b61066d8161065a565b811461067857600080fd5b50565b60008135905061068a81610664565b92915050565b6000602082840312156106a6576106a5610420565b5b60006106b48482850161067b565b91505092915050565b600060408201905081810360008301526106d781856105ff565b905081810360208301526106eb81846105ff565b90509392505050565b6000806040838503121561070b5761070a610420565b5b600083013567ffffffffffffffff81111561072957610728610425565b5b61073585828601610542565b925050602083013567ffffffffffffffff81111561075657610755610425565b5b61076285828601610542565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806107b357607f821691505b6020821081036107c6576107c561076c565b5b50919050565b600081905092915050565b60006107e2826105b9565b6107ec81856107cc565b93506107fc8185602086016105d5565b80840191505092915050565b600061081482846107d7565b915081905092915050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026108817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610844565b61088b8683610844565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b60006108d26108cd6108c8846108a3565b6108ad565b6108a3565b9050919050565b6000819050919050565b6108ec836108b7565b6109006108f8826108d9565b848454610851565b825550505050565b600090565b610915610908565b6109208184846108e3565b505050565b5b818110156109445761093960008261090d565b600181019050610926565b5050565b601f8211156109895761095a8161081f565b61096384610834565b81016020851015610972578190505b61098661097e85610834565b830182610925565b50505b505050565b600082821c905092915050565b60006109ac6000198460080261098e565b1980831691505092915050565b60006109c5838361099b565b9150826002028217905092915050565b6109de826105b9565b67ffffffffffffffff8111156109f7576109f6610445565b5b610a01825461079b565b610a0c828285610948565b600060209050601f831160018114610a3f5760008415610a2d578287015190505b610a3785826109b9565b865550610a9f565b601f198416610a4d8661081f565b60005b82811015610a7557848901518255600182019150602085019450602081019050610a50565b86831015610a925784890151610a8e601f89168261099b565b8355505b6001600288020188555050505b50505050505056fea2646970667358221220043d4771ed3e51eed661a6ccdc0a16a0402ebefdf51fe1c9d698a7089800653264736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DIDDOCUMENTS = "didDocuments";

    public static final String FUNC_GETDIDDOCUMENTBYTXHASH = "getDIDDocumentByTxHash";

    public static final String FUNC_STOREDIDDOCUMENT = "storeDIDDocument";

    public static final String FUNC_TXHASHTODID = "txHashToDID";

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

    public RemoteFunctionCall<String> didDocuments(String param0) {
        final Function function = new Function(FUNC_DIDDOCUMENTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<String, String>> getDIDDocumentByTxHash(byte[] txHash) {
        final Function function = new Function(FUNC_GETDIDDOCUMENTBYTXHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(txHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> storeDIDDocument(String did,
            String subjectToken) {
        final Function function = new Function(
                FUNC_STOREDIDDOCUMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(did), 
                new org.web3j.abi.datatypes.Utf8String(subjectToken)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> txHashToDID(byte[] param0) {
        final Function function = new Function(FUNC_TXHASHTODID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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
