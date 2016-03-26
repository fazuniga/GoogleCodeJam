package general;
import gcj.Problem;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* @author fazuniga */
public class GCJProblem
{
    private String fileName, fileOut;
    private int LinesPerBlock;
    private List<String> lines;

    private int T;
    private List<List<String>> Cases;        
    private PrintWriter pw;

    public GCJProblem() {}
    
    public int getT() { return T; }
    public void setT(int T) { this.T = T; }

    public void setLinesPerBlock(int LinesPerBlock) { this.LinesPerBlock = LinesPerBlock; }
    
    public List<List<String>> getCases() { return Cases; }
    public void setCases(List<List<String>> Cases) { this.Cases = Cases; }

    public PrintWriter getPw() { return pw; }
    public void setPw(PrintWriter pw) { this.pw = pw; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileOut() { return fileOut; }
    public void setFileOut(String fileOut) { this.fileOut = fileOut; }

    public List<String> getLines() { return lines; }
    public void setLines(List<String> lines) { this.lines = lines; }
    
    public void setParameters(String Path, String InputFile, int LinesPerBlock) {
        this.LinesPerBlock = LinesPerBlock;
        
        this.fileName = new File(new File(Path), InputFile).getPath();
        this.fileOut = new File(Path + InputFile.split("\\.")[0] + ".out").getPath();
    }
    
    public void PrepareProblem() throws IOException {
        this.ReadInputFile();
        this.BuildTestCases();
        this.OpenWriter();
    }
    
    public void ReadInputFile() throws IOException {
        this.lines = Files.lines(Paths.get(this.fileName)).collect(Collectors.toList());
    }
    public void BuildTestCases() {
        this.T = Integer.parseInt(this.lines.get(0));

        // Build Test Cases
        this.Cases = new ArrayList<>(this.T);
        for (int iT = 0; iT < this.T; iT++)
        {
            this.Cases.add(this.lines
                                .stream()
                                .skip(iT * this.LinesPerBlock + 1)
                                .limit(this.LinesPerBlock)
                                .collect(Collectors.toList())
            );
        }
    }
    public void OpenWriter() throws IOException {
        this.pw = new PrintWriter(new File(fileOut));
    }
    public void CloseWriter() throws IOException {
        this.pw.close();
    }

    public void Run() {
        try {
            Problem.CodysJam(this); // Acá se reemplaza por el que resuelve el problema
            
            this.CloseWriter();
            
        } catch (IOException ex) { ex.printStackTrace(); }
    }

}