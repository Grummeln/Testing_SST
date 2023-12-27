package ro.uvt.dp.AccountPack;

public interface AbstractFactory {
    ro.uvt.dp.AccountPack.Account createEURAccount(String accountNumber, double sum) throws Exception;
    ro.uvt.dp.AccountPack.Account createRONAccount(String accountNumber, double sum) throws Exception;

}