package br.ufg.inf.avadoc.persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.avadoc.modelo.Docente;
import br.ufg.inf.avadoc.modelo.Nota;
import br.ufg.inf.avadoc.modelo.Usuario;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeAdministrativaRepresentacao;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeEnsino;
import br.ufg.inf.avadoc.modelo.atividade.AtividadeOutra;
import br.ufg.inf.avadoc.modelo.atividade.AtividadePesquisaExtensao;
import br.ufg.inf.avadoc.modelo.atividade.ExtratoAtividades;
import br.ufg.inf.avadoc.modelo.atividade.Producao;
import br.ufg.inf.avadoc.modelo.atividade.ProducaoIntelectual;
import br.ufg.inf.avadoc.modelo.atividade.Produto;
import br.ufg.inf.avadoc.persistencia.dao.ExtratoAtividadesDAO;
import br.ufg.inf.avadoc.persistencia.dao.UsuarioDAO;
import br.ufg.inf.avadoc.persistencia.impl.ExtratoAtividadesDAOImpl;
import br.ufg.inf.avadoc.persistencia.impl.UsuarioDAOImpl;
import br.ufg.inf.avadoc.xml.XmlExtratoAtividades;

/**
 * Testes de persistência
 *
 */
public class DAOTest {
	/**
	 * Necessário para se conectar ao banco de dados
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Retorna configurações de conexão com o banco de dados (Postgre ou MySQL)
	 * @return sessionFactory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			mySqlCreate();
			AnnotationConfiguration configuration = new AnnotationConfiguration();
			configuration.addAnnotatedClass(Usuario.class);
			configuration.setProperty("hibernate.dialect",
					"org.hibernate.dialect.MySQLDialect");
			configuration.setProperty("hibernate.connection.driver_class",
					"com.mysql.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url",
					"jdbc:mysql://localhost/avadoctest");
			configuration.setProperty("hibernate.connection.username", "root");
			configuration.setProperty("hibernate.connection.password", "admin");
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
			configuration.addAnnotatedClass(Docente.class);
			configuration
					.addAnnotatedClass(AtividadeAdministrativaRepresentacao.class);
			configuration.addAnnotatedClass(AtividadeEnsino.class);
			configuration.addAnnotatedClass(AtividadeOutra.class);
			configuration.addAnnotatedClass(AtividadePesquisaExtensao.class);
			configuration.addAnnotatedClass(ProducaoIntelectual.class);
			configuration.addAnnotatedClass(Producao.class);
			configuration.addAnnotatedClass(Produto.class);
			configuration.addAnnotatedClass(ExtratoAtividades.class);
			configuration.addAnnotatedClass(Nota.class);

			System.out.println("MySql");
			return configuration.buildSessionFactory();
		} catch (Throwable ex) {
			postgreSqlCreate();
			AnnotationConfiguration configuration = new AnnotationConfiguration();
			configuration.addAnnotatedClass(Usuario.class);
			configuration.setProperty("hibernate.dialect",
					"org.hibernate.dialect.PostgreSQLDialect");
			configuration.setProperty("hibernate.connection.driver_class",
					"org.postgresql.Driver");
			configuration.setProperty("hibernate.connection.url",
					"jdbc:postgresql://localhost:5432/avadoctest");
			configuration.setProperty("hibernate.connection.username",
					"postgres");
			configuration.setProperty("hibernate.connection.password",
					"postgres");
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
			configuration.addAnnotatedClass(Docente.class);
			configuration
					.addAnnotatedClass(AtividadeAdministrativaRepresentacao.class);
			configuration.addAnnotatedClass(AtividadeEnsino.class);
			configuration.addAnnotatedClass(AtividadeOutra.class);
			configuration.addAnnotatedClass(AtividadePesquisaExtensao.class);
			configuration.addAnnotatedClass(ProducaoIntelectual.class);
			configuration.addAnnotatedClass(Producao.class);
			configuration.addAnnotatedClass(Produto.class);
			configuration.addAnnotatedClass(ExtratoAtividades.class);

			System.out.println("Postgres");
			return configuration.buildSessionFactory();
		}
	}
	
	//@Before // linha comentada para permitir construção no Hudson
	public void setUp() throws Exception {
		sessionFactory = buildSessionFactory();
	}
	
	//@After // linha comentada para permitir construção no Hudson
	public void tearDown() {
		mySqlDrop();
		postgreSqlDrop();
	}

	//@Test // linha comentada para permitir construção no Hudson
	/**
	 * Teste de inserção de usuário
	 */
	public void inserirUsuarioTest() {
		UsuarioDAO dao = new UsuarioDAOImpl(sessionFactory);
		Usuario u = new Usuario();
		u.setNome("usuario teste");
		Long id = (Long) dao.inserir(u);
		Usuario usuarioObtido = dao.obter(id);
		assertNotNull(usuarioObtido);
		assertEquals("usuario teste", usuarioObtido.getNome());
	}
	
	//@Test // linha comentada para permitir construção no Hudson
	/**
	 * Teste de alteração de usuário
	 */
	public void atualizarUsuarioTest() {
		UsuarioDAO dao = new UsuarioDAOImpl(sessionFactory);
		Usuario u = new Usuario();
		u.setNome("usuario teste atualização");
		Long id = (Long) dao.inserir(u);
		
		Usuario usuarioAAtualizar = dao.obter(id);
		assertEquals("usuario teste atualização", usuarioAAtualizar.getNome());
		
		usuarioAAtualizar.setNome("usuario teste atualizado");
		usuarioAAtualizar.setLogin("usuarioTeste");
		usuarioAAtualizar.setSenha("senhaHASH");
		dao.alterar(usuarioAAtualizar);
		
		Usuario usuarioAtualizado = dao.obter(id);
		
		assertNotNull(usuarioAtualizado);
		assertEquals("usuario teste atualizado", usuarioAtualizado.getNome());
		assertEquals("usuarioTeste", usuarioAtualizado.getLogin());
		assertEquals("senhaHASH", usuarioAtualizado.getSenha());
	}
	
	//@Test // linha comentada para permitir construção no Hudson
	/**
	 * Teste de exclusão de usuário
	 */
	public void excluirUsuarioTest() {
		UsuarioDAO dao = new UsuarioDAOImpl(sessionFactory);
		Usuario u = new Usuario();
		u.setNome("usuario teste exclusão");
		Long id = (Long) dao.inserir(u);
		
		Usuario usuarioARemover = dao.obter(id);
		assertEquals("usuario teste exclusão", usuarioARemover.getNome());
		
		dao.remover(usuarioARemover);
		usuarioARemover = dao.obter(id);
		assertEquals(null, usuarioARemover);
	}
	
	//@Test // linha comentada para permitir construção no Hudson
	/**
	 * Teste de inserção de extrato de atividades
	 */
	public void inserirExtratoAtividadesTest() {
		ExtratoAtividadesDAO dao = new ExtratoAtividadesDAOImpl(sessionFactory);
		ExtratoAtividades extratoAtividades = new ExtratoAtividades();
		extratoAtividades = exemploExtrato();
		Long id = (Long) dao.inserir(extratoAtividades);
		
		ExtratoAtividades extrato = dao.obter(id);
		
		assertNotNull(extrato);
		
		AtividadeEnsino atividadeEnsino = extrato.getAtividadeEnsino();
		ProducaoIntelectual producaoIntelectual = extrato
				.getProducaoIntelectual();
		AtividadePesquisaExtensao atividadePesquisaExtensao = extrato
				.getAtividadePesquisaExtensao();
		AtividadeAdministrativaRepresentacao atividadeAdministrativaRepresentacao = extrato
				.getAtividadeAdministrativaRepresentacao();
		AtividadeOutra atividadeOutra = extrato.getAtividadeOutra();
		
		assertNotNull(extrato);
		
		assertEquals("12345", extrato.getDocente().getMatricula());
		
		assertEquals(200, atividadeEnsino.getPontosGraduacao());
		assertEquals(200, atividadeEnsino.getPontosPosGraduacao());
		assertEquals(400, atividadeEnsino.getPontos());
		
		assertEquals(66, producaoIntelectual.getProducaoCientifica().getPontos());
		assertEquals(38, producaoIntelectual.getProducaoArtisticaCultural().getPontos());
		assertEquals(54, producaoIntelectual.getProducaoTecnicaTecnologica().getPontos());
		assertEquals(9, producaoIntelectual.getProducaoOutra().getPontos());
		assertEquals(167, producaoIntelectual.getPontos());
		
		assertEquals(30, atividadePesquisaExtensao.getPesquisa().getPontos());
		assertEquals(25, atividadePesquisaExtensao.getExtensao().getPontos());
		assertEquals(55, atividadePesquisaExtensao.getPontos());
		
		assertEquals(120, atividadeAdministrativaRepresentacao.getDirecaoFuncaoGratificada().getPontos());
		assertEquals(14, atividadeAdministrativaRepresentacao.getAdministrativa().getPontos());
		assertEquals(71, atividadeAdministrativaRepresentacao.getOutraAdministrativa().getPontos());
		assertEquals(18, atividadeAdministrativaRepresentacao.getRepresentacaoFora().getPontos());
		assertEquals(223, atividadeAdministrativaRepresentacao.getPontos());
		
		assertEquals(28, atividadeOutra.getOrientacao().getPontos());
		assertEquals(6, atividadeOutra.getBancaCurso().getPontos());
		assertEquals(13, atividadeOutra.getAprendizadoAperfeicoamento().getPontos());
		assertEquals(47, atividadeOutra.getPontos());
		
		assertEquals(892, extrato.getPontos());
	}
	
	//@Test // linha comentada para permitir construção no Hudson
	/**
	 * Teste de alteração de extrato de atividades
	 */
	public void atualizarExtratoAtividadesTest() {
		ExtratoAtividadesDAO dao = new ExtratoAtividadesDAOImpl(sessionFactory);
		ExtratoAtividades extratoAtividades = new ExtratoAtividades();
		extratoAtividades = exemploExtrato();
		
		Long id = (Long) dao.inserir(extratoAtividades);
		ExtratoAtividades extrato = dao.obter(id);
		
		Date dataNova = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dataNova = dateFormat.parse("31/12/2013");
			extrato.setDataFinal(dataNova);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		dao.alterar(extrato);
		
		ExtratoAtividades extratoAlterado = dao.obter(id);
		assertNotNull(extratoAlterado);
		assertEquals(dataNova, extratoAlterado.getDataFinal());
	}
	
	//@Test // linha comentada para permitir construção no Hudson
	/**
	 * Teste de exclusão de extrato de atividades
	 */
	public void excluirExtratoAtividadesTest() {
		ExtratoAtividadesDAO dao = new ExtratoAtividadesDAOImpl(sessionFactory);
		ExtratoAtividades extratoAtividades = new ExtratoAtividades();
		extratoAtividades = exemploExtrato();
		extratoAtividades.getDocente().setMatricula("99");
		Long id = (Long) dao.inserir(extratoAtividades);
		
		ExtratoAtividades extrato = dao.obter(id);
		dao.remover(extrato);
		
		ExtratoAtividades extratoExcluido = dao.obter(id);
		assertEquals(null, extratoExcluido);
	}

	/**
	 * Extrato de atividades para teste
	 * @return extrato
	 */
	private static ExtratoAtividades exemploExtrato(){
		String xml = null;

		try {
			InputStream inputStream = ClassLoader
					.getSystemResourceAsStream("exemploExtratoXML.xml");
			xml = IOUtils.toString(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XmlExtratoAtividades.getExtrato(xml);
	}
	
	/**
	 * Cria ou exclui uma base de dados
	 * 
	 * @param comando
	 *            CREATE or DROP
	 */
	private static void databasePG(String comando){
		try {
			Connection Conn = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/?user=postgres&password=postgres");
			Statement s = Conn.createStatement();
			s.executeUpdate(comando + " DATABASE avadoctest");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Cria ou exclui uma base de dados
	 * 
	 * @param comando
	 *            CREATE or DROP
	 */
	private static void databaseMySql(String comando) {
		try {
			Connection Conn = DriverManager
					.getConnection("jdbc:mysql://localhost/?user=root&password=admin");
			Statement s = Conn.createStatement();
			s.executeUpdate(comando + " DATABASE avadoctest");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	private static void postgreSqlCreate() {
		databasePG("CREATE");
	}
	private static void mySqlCreate() {
		databaseMySql("CREATE");
	}
	private void mySqlDrop() {
		databaseMySql("DROP");
	}
	private void postgreSqlDrop() {
		databasePG("DROP");
	}
}
