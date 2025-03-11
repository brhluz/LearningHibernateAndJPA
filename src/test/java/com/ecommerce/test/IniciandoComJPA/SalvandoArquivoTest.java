package com.ecommerce.test.IniciandoComJPA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;

import com.algaworksEstudo.ecommerce.model.NotaFiscal;
import com.algaworksEstudo.ecommerce.model.Pedido;
import com.algaworksEstudo.ecommerce.model.Produto;
import com.ecommerce.test.EntityManagerTest;

public class SalvandoArquivoTest extends EntityManagerTest{

	@Test
	public void salvarXmlNota() {
		Pedido pedido = em.find(Pedido.class, 1);
		
		NotaFiscal NF = new NotaFiscal();
		NF.setPedidoId(pedido);
		NF.setDataEmissao(new Date());
		NF.setXml(carregarNotaFiscal());
		
	}
	
	public void salvarFotoProduto() {
		Produto produto = em.find(Produto.class, 1);
		produto.setFotoProduto(carregarFoto());
		// transação persist/merge
	}
	
	private static byte[] carregarNotaFiscal() {
		return carregarArquivo("/nota-fiscal.xml");
	}
	
	private static byte[] carregarArquivo(String nome) {
//		return SalvandoArquivosTest.class.getResourceAsStream(nome).readAllBytes();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (InputStream inputStream = SalvandoArquivoTest.class.getResourceAsStream(nome)) {
            if (inputStream == null) {
                throw new IOException("Recurso não encontrado: " + nome);
            }
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Aqui você pode adicionar o tratamento de exceção apropriado
        }
        return buffer.toByteArray();
    }

	private static byte[] carregarFoto() {
		return carregarArquivo("/kindle.jpg");
	}
}
