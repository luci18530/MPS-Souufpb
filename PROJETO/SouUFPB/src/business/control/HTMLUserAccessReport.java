package business.control;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HTMLUserAccessReport extends UserAccessReport {

    @Override
    protected List<String> getUsers() {
        // Lógica para obter a lista de usuários
    }

    @Override
    protected int getTotalAccessCount(String user) {
        // Lógica para obter o número total de acessos do usuário
    }

    @Override
    protected int getUniqueAccessCount(String user) {
        // Lógica para obter o número de acessos únicos do usuário
    }

    @Override
    protected FileWriter openFile(String fileName) {
        String nomeArquivo = fileName + ".html";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return fileWriter;
    }

    @Override
    protected void generateBody(String user, int totalAccessCount, int uniqueAccessCount) {
        // Lógica para gerar o corpo do relatório em HTML para cada usuário
    }

    @Override
    protected void closeFile(String fileName) {
    }
}