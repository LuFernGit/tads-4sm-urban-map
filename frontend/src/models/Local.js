export default class Local {
  constructor({
    id,
    nome,
    descricao,
    fotosUrl = [],
    endereco,
    cidade,
    estado,
    cep,
    latitude,
    longitude,
    likes = 0,
    salvos = 0,
    tags = [],
    curtidoPeloUsuario = false,
    salvoPeloUsuario = false,
  }) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.fotosUrl = fotosUrl;
    this.endereco = endereco;
    this.cidade = cidade;
    this.estado = estado;
    this.cep = cep;
    this.latitude = latitude;
    this.longitude = longitude;
    this.likes = likes;
    this.salvos = salvos;
    this.tags = tags;
    this.curtidoPeloUsuario = curtidoPeloUsuario;
    this.salvoPeloUsuario = salvoPeloUsuario;
  }
}