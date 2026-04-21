import Local from "../models/Local";

export const locaisMock = [

  new Local({
    id: 1,
    nome: "Beto Carrero World",
    descricao: "Maior parque temático da América Latina",
    fotosUrl: [require("../assets/Locais/betocarrero.jpg")],
    endereco: "Penha - SC",
    cidade: "Penha",
    estado: "SC",
    cep: "",
    latitude: -26.7998,
    longitude: -48.6137,
    qtdLike: 500,
    qtdSalvo: 200,
    tags: [{ id: 1, nome: "parque", categoria: "lazer", cor: "#00ff00" }],
    curtidoPeloUsuario: false,
    salvoPeloUsuario: false,
  }),

/* --------------------------- */

  new Local({
    id: 2,
    nome: "Campos do Jordão",
    descricao: "Destino turístico de inverno em SP",
    fotosUrl: [require("../assets/Locais/camposJordao.jpg")],
    endereco: "Campos do Jordão - SP",
    cidade: "Campos do Jordão",
    estado: "SP",
    cep: "",
    latitude: -22.7551,
    longitude: -45.6099,
    qtdLike: 320,
    qtdSalvo: 150,
    tags: [{ id: 2, nome: "montanha", categoria: "natureza", cor: "#00aaff" }],
    curtidoPeloUsuario: false,
    salvoPeloUsuario: true,
  }),

/* --------------------------- */

  new Local({
    id: 3,
    nome: "Cristo Redentor",
    descricao: "Uma das 7 maravilhas do mundo moderno",
    fotosUrl: [
      require("../assets/Locais/cristo redentor1.jpg"),
      require("../assets/Locais/cristo redentor2.jpg"),
      require("../assets/Locais/cristo redentor3.jpg"),
    ],
    endereco: "Rio de Janeiro - RJ",
    cidade: "Rio de Janeiro",
    estado: "RJ",
    cep: "",
    latitude: -22.9519,
    longitude: -43.2105,
    qtdLike: 1200,
    qtdSalvo: 800,
    tags: [{ id: 3, nome: "turismo", categoria: "ponto turistico", cor: "#ffcc00" }],
    curtidoPeloUsuario: true,
    salvoPeloUsuario: true,
  }),

/* --------------------------- */

  new Local({
    id: 4,
    nome: "Japan House",
    descricao: "Centro cultural japonês em São Paulo",
    fotosUrl: [
      require("../assets/Locais/japanhouse1.jpg"),
      require("../assets/Locais/japanhouse2.jpg"),
    ],
    endereco: "Av. Paulista - SP",
    cidade: "São Paulo",
    estado: "SP",
    cep: "",
    latitude: -23.5705,
    longitude: -46.6446,
    qtdLike: 280,
    qtdSalvo: 120,
    tags: [{ id: 4, nome: "cultura", categoria: "arte", cor: "#ff0000" }],
    curtidoPeloUsuario: false,
    salvoPeloUsuario: false,
  }),

/* --------------------------- */

  new Local({
    id: 5,
    nome: "MASP",
    descricao: "Museu de Arte de São Paulo",
    fotosUrl: [require("../assets/Locais/masp.jpg")],
    endereco: "Av. Paulista - SP",
    cidade: "São Paulo",
    estado: "SP",
    cep: "",
    latitude: -23.5614,
    longitude: -46.6559,
    qtdLike: 900,
    qtdSalvo: 500,
    tags: [{ id: 5, nome: "museu", categoria: "cultura", cor: "#ff0000" }],
    curtidoPeloUsuario: true,
    salvoPeloUsuario: false,
  }),

/* --------------------------- */

  new Local({
    id: 6,
    nome: "Museu do Ipiranga",
    descricao: "Museu histórico da independência do Brasil",
    fotosUrl: [
      require("../assets/Locais/museuIpiranga1.jpg"),
      require("../assets/Locais/museuIpiranga2.jpg"),
      require("../assets/Locais/museuIpiranga3.jpg"),
    ],
    endereco: "São Paulo - SP",
    cidade: "São Paulo",
    estado: "SP",
    cep: "",
    latitude: -23.5858,
    longitude: -46.6097,
    qtdLike: 650,
    qtdSalvo: 300,
    tags: [{ id: 6, nome: "história", categoria: "cultura", cor: "#8b4513" }],
    curtidoPeloUsuario: false,
    salvoPeloUsuario: true,
  }),

/* --------------------------- */

  new Local({
    id: 7,
    nome: "Parque Ibirapuera",
    descricao: "Um dos maiores parques urbanos de São Paulo",
    fotosUrl: [
      require("../assets/Locais/ibirapuera1.jpg"),
      require("../assets/Locais/ibirapuera2.jpg"),
      require("../assets/Locais/ibirapuera3.jpg"),
    ],
    endereco: "Av. Pedro Álvares Cabral - SP",
    cidade: "São Paulo",
    estado: "SP",
    cep: "",
    latitude: -23.5874, // valor real do parque
    longitude: -46.6576,
    qtdLike: 1200,
    qtdSalvo: 700,
    tags: [{ id: 7, nome: "parque", categoria: "lazer", cor: "#00ff00" }],
    curtidoPeloUsuario: true,
    salvoPeloUsuario: true,
  }),

/* --------------------------- */

  new Local({
    id: 8,
    nome: "Praia de Copacabana",
    descricao: "Uma das praias mais famosas do mundo",
    fotosUrl: [
      require("../assets/Locais/copacabana1.webp"),
      require("../assets/Locais/copacabana2.png"),
    ],
    endereco: "Rio de Janeiro - RJ",
    cidade: "Rio de Janeiro",
    estado: "RJ",
    cep: "",
    latitude: -22.9739,
    longitude: -43.1853,
    qtdLike: 2000,
    qtdSalvo: 1500,
    tags: [{ id: 8, nome: "praia", categoria: "lazer", cor: "#00bfff" }],
    curtidoPeloUsuario: false,
    salvoPeloUsuario: false,
  }),

];