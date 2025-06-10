package com.cardapio.qrmenu.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos") // Nome da tabela no banco
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(columnDefinition = "TEXT") // Para descrições longas
    private String descricao;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @Column(name = "url_imagem", length = 255) // Padrão para caminhos de arquivo
    private String urlImagem;
    
    @Column(nullable = false)
    @Builder.Default // Garante que o valor padrão seja respeitado mesmo usando @Builder
    private Boolean disponivel = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_categoria"))
    private Categoria categoria;
    
    // Método auxiliar para atualização parcial (útil em PATCH)
    public void atualizarDados(Produto produtoAtualizado) {
        if (produtoAtualizado.getNome() != null) {
            this.nome = produtoAtualizado.getNome();
        }
        if (produtoAtualizado.getDescricao() != null) {
            this.descricao = produtoAtualizado.getDescricao();
        }
        if (produtoAtualizado.getPreco() != null) {
            this.preco = produtoAtualizado.getPreco();
        }
        if (produtoAtualizado.getUrlImagem() != null) {
            this.urlImagem = produtoAtualizado.getUrlImagem();
        }
        if (produtoAtualizado.getDisponivel() != null) {
            this.disponivel = produtoAtualizado.getDisponivel();
        }
    }
}