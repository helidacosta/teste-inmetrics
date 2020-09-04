package br.com.inmetrics.teste.support;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps {
	
	  WebDriver driver;			

	  @Given("^Eu abro o navegador$")
	  public void Eu_abro_o_navegador(){	
		  DesiredCapabilities capabilities = DesiredCapabilities.chrome();
  	      ChromeOptions options = new ChromeOptions();
  	      capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
  	      capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
  	      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
          System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver");
            driver = new ChromeDriver();			
	        driver.manage().window().maximize();			
	        driver.get("http://www.inmrobo.tk/accounts/login/");					
	    }
	    

	 @When("^Eu cadastro o seguinte usuario$")
	    public void Eu_cadastro_o_seguinte_usuario() {
		driver.findElement(By.className("nav-link text-uppercase font-weight-boldnav-link text-uppercase font-weight-bold")).click();
		driver.findElement(By.name("username")).sendKeys("Helida.Costa");
		driver.findElement(By.name("pass")).sendKeys("1234");
		driver.findElement(By.name("confirmpass")).sendKeys("1234");
		driver.findElement(By.className("login100-form-btn")).click();
	 }
	 

	 @When("^Eu logo o seguinte usuario$")
	    public void Eu_logo_o_seguinte_usuario()  {
		driver.findElement(By.name("username")).sendKeys("Helida.Costa");
		driver.findElement(By.name("pass")).sendKeys("1234");
		driver.findElement(By.className("login100-form-btn")).click();
	 }
	 
	 @Given("^Entro no cadastro do funcionario$")
	 	public void Entro_no_cadastro_do_funcionario() {
		 driver.findElement(By.xpath("//div[@id='navbarSupportedContent']//ul[@class='navbar-nav ml-auto']//li[@class='nav-item']//a[contains(text(),'Novo Funcionário')]")).click();
	    }
	 
	 
	 @When("^Eu cadastro o funcionario$")
	    public void Eu_cadastro_o_funcionario(){ 
		 	driver.findElement(By.name("nome")).sendKeys("Luis Costa");
		 	driver.findElement(By.name("cpf")).sendKeys("416.393.868-07");
		 	Select dropdown = new Select(driver.findElement(By.id("slctSexo")));
		 	dropdown.selectByVisibleText("Feminino");
		 	driver.findElement(By.name("admissao")).sendKeys("01/09/2020");
		 	driver.findElement(By.name("cargo")).sendKeys("Analista de Testes");
		 	driver.findElement(By.name("salario")).sendKeys("5,000");
		 	driver.findElement(By.id("clt")).click();
		 	driver.findElement(By.className("cadastrar-form-btn")).click();
	 }
	 
	 @Then("^Eu recebo uma mensagem de sucesso funcionario$")
	 public void Eu_recebo_uma_mensagem_de_sucesso_funcionario()  {
			String textoElement = driver.findElement(By.className("alert alert-success alert-dismissible fade show")).getText();
			assertEquals("SUCESSO! Usuário cadastrado com sucesso", textoElement);
		 }
	 
	 @When("^Eu edito o funcionario$")
	    public void Eu_edito_o_funcionario() {
		driver.findElement(By.xpath("//*[@id=\"tabela_filter\"]/label/input")).sendKeys("Luis Costa");
		String nome = driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[1]/td[1]")).getText();
		assertEquals("Luis Costa", nome);
		driver.findElement(By.xpath("//a[@href='/empregados/edit/2236']")).click();
		driver.findElement(By.name("nome")).sendKeys(" Campos");
	 	Select dropdown = new Select(driver.findElement(By.id("slctSexo")));
	 	dropdown.selectByVisibleText("Masculino");
		driver.findElement(By.name("admissao")).sendKeys("01/08/2020");
		driver.findElement(By.name("cargo")).clear();
		driver.findElement(By.name("cargo")).sendKeys("Analista de Automação de Testes");
		driver.findElement(By.id("pj")).click();
		driver.findElement(By.className("cadastrar-form-btn")).click();
	 }
	 
	 @Then("^Eu recebo uma mensagem de sucesso funcionario edicao$")
	 public void Eu_recebo_uma_mensagem_de_sucesso__funcionario_edicao() {
			String textoElement = driver.findElement(By.className("alert alert-success alert-dismissible fade show")).getText();
			assertEquals("SUCESSO! Informações atualizadas com sucesso", textoElement);
		 }
	 
	 @When("^Eu removo o funcionario$")
	    public void Eu_removo_o_funcionario()  {
		driver.findElement(By.xpath("//*[@id=\"tabela_filter\"]/label/input")).sendKeys("Luis Costa");
		String nome = driver.findElement(By.xpath("//*[@id=\"tabela\"]/tbody/tr[1]/td[1]")).getText();
		assertEquals("Luis Costa", nome);
		driver.findElement(By.id("delete-btn")).click();
	 }
	 
	 @Then("^Eu recebo uma mensagem de sucesso funcionario remocao$")
	 public void Eu_recebo_uma_mensagem_de_sucesso__funcionario_remocao() {
			String textoElement = driver.findElement(By.className("alert alert-success alert-dismissible fade show")).getText();
			assertEquals("SUCESSO! Funcionário removido com sucesso", textoElement);
		 }
}
