package br.com.senac.urbanmap.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImagemService {

    @Value("${urbanmap.upload.dir-imagens}")
    private String diretorioImagens;

    public String salvarImagem(MultipartFile arquivo, String subPasta) {
        try {
            Path diretorioDestino = Paths.get(this.diretorioImagens)
                    .resolve(subPasta)
                    .toAbsolutePath()
                    .normalize();

            if (!Files.exists(diretorioDestino)) {
                Files.createDirectories(diretorioDestino);
            }

            String nomeArquivo = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();
            Path caminhoCompleto = diretorioDestino.resolve(nomeArquivo);

            Files.copy(arquivo.getInputStream(), caminhoCompleto);

            return subPasta + "/" + nomeArquivo;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public void excluirImagem(String caminhoRelativo) {
        if (caminhoRelativo == null || caminhoRelativo.isEmpty()) {
            return;
        }
        try {
            Path arquivoParaDeletar = Paths.get(this.diretorioImagens)
                    .resolve(caminhoRelativo)
                    .toAbsolutePath()
                    .normalize();

            Files.deleteIfExists(arquivoParaDeletar);
        } catch (IOException e) {

        }
    }


}
