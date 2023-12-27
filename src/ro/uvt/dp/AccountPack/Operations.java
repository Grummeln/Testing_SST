package ro.uvt.dp.AccountPack;

public interface Operations {
	void execute() throws Exception;

	void undo() throws Exception;

	void redo() throws Exception;

	public double getTotalAmount();

	public double getInterest();

	public void depose(double amount) throws Exception;

	public void retrieve(double amount) throws Exception;
}
