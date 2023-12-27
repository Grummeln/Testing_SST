package ro.uvt.dp.AccountPack;

public interface AbstractFactory {
    Account createEURAccount(String accountNumber, double sum) throws Exception;
    Account createRONAccount(String accountNumber, double sum) throws Exception;

}