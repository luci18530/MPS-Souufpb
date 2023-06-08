package business.control;

// Importando as classes necessárias
import infra.InfraException;
import infra.CursoFile;
import business.model.Curso;
import factory.CursoFactory;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Definindo a classe CursoManager
public class CursoManager {
    // Inicializando um Map para armazenar os cursos e uma instância da classe CursoFile
    private Map<String, Curso> cursos;
    private CursoFile cursoFile;
    private CursoFactory cursoFactory;

    // Construtor da classe que lança uma exceção do tipo InfraException
    public CursoManager(CursoFactory cursoFactory) throws InfraException{
        // Instanciando um novo CursoFile e carregando os cursos do arquivo
        this.cursoFactory = cursoFactory;
        cursoFile = new CursoFile();
        cursos = cursoFile.carregarCursos();
    }

    // Método para adicionar um curso
    public void adicionarCurso(String[] args){
        // Adicionando um novo Curso no Map 'cursos' e salvando no arquivo
        Curso curso = cursoFactory.createCurso(args[0], args[1], args[2], args[3]);
        cursos.put(args[0], curso);
        cursoFile.salvarCursos(cursos);
    }

    // Método para remover um curso
    public void removerCurso(String nome) throws InfraException{
        // Verifica se o curso existe no Map antes de tentar remover
        if(!cursos.containsKey(nome)){
            throw new IllegalArgumentException("Curso não encontrado. Tente novamente.");
        }

        // Remove o curso do Map 'cursos' e salva no arquivo
        cursos.remove(nome);
        cursoFile.salvarCursos(cursos);
    }

    // Método para retornar os cursos
    public Map<String,Curso> getCursos() throws InfraException{
        Logger logger = cursoFile.getLogger();

        try{
            // Carrega os cursos do arquivo
            Map<String,Curso> meusCursos = cursoFile.carregarCursos();
            return meusCursos;

        } catch(NullPointerException ex){
            // Registra a exceção e lança uma nova InfraException
            logger.severe(ex.getMessage());
            throw new InfraException("Erro na exibição. Contate o admnistrador ou tente mais tarde.");
        }
    }

    public List<Curso> getCursosPorArea(String area) throws InfraException {
        return this.getCursos().values().stream()
            .filter(curso -> curso.getArea().equals(area))
            .collect(Collectors.toList());
    }
}
