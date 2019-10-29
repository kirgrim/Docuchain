package com.docupace.blockchain;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import rx.Subscription;

import java.io.IOException;
import java.util.List;

import static com.blockchain.hash.getHash;


public class Application {


    public Application() throws IOException, CipherException {
    }


    private void run (MyContract contract, Web3j web3j, String[]args) throws Exception {
        contract.addDocument(getHash(args[0]),args[1]).send();
    }

    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/f9b6d3dade8f4bc8a0d3ed3af471aa9b"));
        Credentials credentials = WalletUtils.loadCredentials(
                        "lumitu5374",
                        WalletUtils.getTestnetKeyDirectory()+"\\UTC--2018-07-16T09-32-18.711666400Z--b95910beb37454edd6ed78d5046aad8609696b73");

        MyContract new_contract = MyContract.load("0xfd00DD73Ade458c2CFE17b960EA8b375028C5FC0", web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        switch (args.length) {
            case 0:
                System.out.println("Use: DocuChain filename [description]");
                break;
            case 1:
                String call = new_contract.getDesc(getHash(args[0])).send();
                System.out.println("hash="+getHash(args[0]));
                System.out.println(call);
                break;
            case 2:
                if(!("".equals(new_contract.getDesc(getHash(args[0])).send()))){
                    System.out.println("this file already exist");
                }
                else new Application().run(new_contract,web3j,args);
                System.out.println("hash="+getHash(args[0]));
                break;
        }
        System.exit(0);

    }

    }
