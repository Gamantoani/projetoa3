package projetoa3;

public class Projeto {
    private int id;
    private String codigo;
    private String descricao;
    private String nomeusuario;
    private String telefone;
    private String dataCriacao;
    private String status;

    // Lista dos 17 ODS
    private static final String[] ODS_LIST = {
        "1- Erradicação da Pobreza",
        "2- Fome Zero e Agricultura Sustentável",
        "3- Saúde e Bem-Estar",
        "4- Educação de Qualidade",
        "5- Igualdade de Gênero",
        "6- Água Potável e Saneamento",
        "7- Energia Limpa e Acessível",
        "8- Trabalho Decente e Crescimento Econômico",
        "9- Indústria, Inovação e Infraestrutura",
        "10- Redução das Desigualdades",
        "11- Cidades e Comunidades Sustentáveis",
        "12- Consumo e Produção Responsáveis",
        "13- Ação Contra a Mudança Global do Clima",
        "14- Vida na Água",
        "15- Vida Terrestre",
        "16- Paz, Justiça e Instituições Eficazes",
        "17- Parcerias e Meios de Implementação"
    };

    public Projeto(int id, String codigo, String descricao, String nomeusuario, String telefone, String datacriacao, String status) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.nomeusuario = nomeusuario;
        this.telefone = telefone;
        this.dataCriacao = datacriacao;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // Getters e Setters para cada atributo

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getNomeUsuario() {
        return nomeusuario;
    }

    public void setNomeUsuario(String nomeusuario) {
        this.nomeusuario = nomeusuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}