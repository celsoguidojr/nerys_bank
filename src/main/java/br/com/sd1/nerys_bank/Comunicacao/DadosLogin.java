package br.com.sd1.nerys_bank.Comunicacao;

public class DadosLogin {
	private static Integer num_conta = 0;
	private static Integer num_agencia = 0;
	private static String senha;
	

	public static Integer getNum_conta() {
		return num_conta;
	}

	public static void setNum_conta(Integer num_conta) {
		DadosLogin.num_conta = num_conta;
	}

	public static Integer getNum_agencia() {
		return num_agencia;
	}

	public static void setNum_agencia(Integer num_agencia) {
		DadosLogin.num_agencia = num_agencia;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		DadosLogin.senha = senha;
	}
	
	

	
}
