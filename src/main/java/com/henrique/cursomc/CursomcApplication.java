package com.henrique.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.henrique.cursomc.domain.Categoria;
import com.henrique.cursomc.domain.Cidade;
import com.henrique.cursomc.domain.Cliente;
import com.henrique.cursomc.domain.Endereco;
import com.henrique.cursomc.domain.Estado;
import com.henrique.cursomc.domain.ItemPedido;
import com.henrique.cursomc.domain.Pagamento;
import com.henrique.cursomc.domain.PagamentoComCartao;
import com.henrique.cursomc.domain.PagametoComBoleto;
import com.henrique.cursomc.domain.Pedido;
import com.henrique.cursomc.domain.Produto;
import com.henrique.cursomc.domain.enums.EstadoPagamento;
import com.henrique.cursomc.domain.enums.TipoCliente;
import com.henrique.cursomc.respositories.CategoriaRepository;
import com.henrique.cursomc.respositories.CidadeRepository;
import com.henrique.cursomc.respositories.ClienteRepository;
import com.henrique.cursomc.respositories.EnderecoRepository;
import com.henrique.cursomc.respositories.EstadoRepository;
import com.henrique.cursomc.respositories.ItemPedidoRepository;
import com.henrique.cursomc.respositories.PagamentoRepository;
import com.henrique.cursomc.respositories.PedidoRepository;
import com.henrique.cursomc.respositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(2, "Financeiro");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1, c2));
		
		categoriaRepository.save(Arrays.asList(c1, c2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", e1);
		Cidade cid2 = new Cidade(null, "São Paulo", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(cid1));
		e2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepository.save(Arrays.asList(e1, e2));
		cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "mara@gail.com", "03965521616", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("32231516", "32234556"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "222", "221", "Centro", "31310-390", cliente1, cid1);
		Endereco endereco2 = new Endereco(null, "Av Matos", "222", "221", "Centro", "31310-390", cliente1, cid2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.save(Arrays.asList(cliente1));
		enderecoRepository.save(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pedido1 = new Pedido(null, format.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, format.parse("10/10/2016 09:39"), cliente1, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 3);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagametoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, format.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.save(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.save(Arrays.asList(pagamento1, pagamento2));
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, p1, 1, 0.00, 2000.0);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, p3, 1, 0.00, 80.0);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, p2, 1, 100.0, 800.0);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		p1.getItens().addAll(Arrays.asList(itemPedido1));
		p2.getItens().addAll(Arrays.asList(itemPedido3));
		p3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.save(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}
	
}
