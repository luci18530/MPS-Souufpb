package view;

import java.util.Map;

import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import business.model.Curso;
import factory.CursoFactory;
import factory.CursoFactoryImpl;
import factory.QuestionarioFactory;
import factory.QuestionarioFactoryImpl;
import infra.InfraException;

public class AdminMenu {
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    public static void show() throws InfraException{
        try {
            CursoFactory cursoFactory = new CursoFactoryImpl();
            cursoManager = new CursoManager(cursoFactory);
            QuestionarioFactory questionarioFactory = new QuestionarioFactoryImpl();
            questionarioManager = new QuestionarioManager(questionarioFactory);

        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        String option = JOptionPane.showInputDialog("Seção admin. Escolha a opção desejada:\n"
                + "1- Adicionar Curso\n"
                + "2- Visualizar Cursos\n"
                + "3- Remover Curso\n"
                + "4- Adicionar Pergunta\n"
                + "5- Visualizar Perguntas\n"
                + "6- Remover Pergunta\n"
                + "7- Sair");

        int choice = Integer.parseInt(option);
        switch (choice) {
            case 1:
                adicionarCurso();
                break;
            case 2:
                visualizarCursos();
                break;
            case 3:
                removerCurso();
                break;
            case 4:
                adicionarPergunta();
                break;
            case 5:
                visualizarPerguntas();
                break;
            case 6:
                removerPergunta();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                show();
                break;
        }
    }

    private static void adicionarCurso() throws InfraException {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso:");
        String cidade = JOptionPane.showInputDialog("Digite a cidade do curso:");
        String centro = JOptionPane.showInputDialog("Digite o centro do curso:");
    
        String[] cursoArgs = {nome, cidade, centro};
        cursoManager.adicionarCurso(cursoArgs);
        JOptionPane.showMessageDialog(null, "Curso adicionado com sucesso!");
    }
    
    private static void visualizarCursos() {
        try {
            Map<String, Curso> cursos = cursoManager.getCursos();
            StringBuilder sb = new StringBuilder();
            for (Curso curso : cursos.values()) {
                sb.append(curso.toString());
                sb.append("\n---\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private static void removerCurso() {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso que deseja remover:");
        try {
            cursoManager.removerCurso(nome);
            JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private static void adicionarPergunta() {
        // Implementação para adicionar uma pergunta
    }

    private static void visualizarPerguntas() {
       // Implementação para visualizar as perguntas
    }

    private static void removerPergunta() {
        // Implementação para remover uma pergunta
    }
    }
    

