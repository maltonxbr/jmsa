package br.ufpr.bioinfo.jmsa.analyser;

import br.ufpr.bioinfo.jmsa.model.OPeaklist;

public class CPeaklistAnalyser
{
    public static double getPeakistSimilarity(OPeaklist peaklistA, OPeaklist peaklistB)
    {
        Double[][] dataA = new Double[2][peaklistA.peaks.size()];
        Double[][] dataB = new Double[2][peaklistB.peaks.size()];
        
        for (int i = 0; i < peaklistA.peaks.size(); i++)
        {
            dataA[0][i] = peaklistA.peaks.get(i).mass;
            dataA[1][i] = 1.0;
        }
        for (int i = 0; i < peaklistB.peaks.size(); i++)
        {
            dataB[0][i] = peaklistB.peaks.get(i).mass;
            dataB[1][i] = 1.0;
        }
        
        Double[][] distMatrix = constructDistMatrix(dataA, dataB);
        Double[][] simMatrix = constructSimMatrix(distMatrix);
        
        return getAverageNeedlemanWunschWithoutScore(simMatrix);
    }
    
    /**
     * @param dataA is a matrix. Rows represent features/time points. Columns represent a sample/time-series;
     * @param dataB is a matrix. Rows represent features/time points. Columns represent a sample/time-series;
     * @return distMatrix: matrix, disimilarity measures
     */
    public static Double[][] constructDistMatrix(Double[][] dataA, Double[][] dataB)
    {
        try
        {
            //Euclidean distance
            return euclidean(dataA, dataB);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param distMatrix: matrix, disimilarity measures
     * @return simMatrix: matrix, similarity measures;
     */
    public static Double[][] constructSimMatrix(Double[][] distMatrix)
    {
        //Gaussian similarity function (transform disimilarity Euclidean to similarity)
        try
        {
            //transform the Euclidean distance into Gaussian Similarity
            Double[][] simMatrix = new Double[distMatrix.length][distMatrix[0].length];
            for (int simMatrixRow = 0; simMatrixRow < simMatrix.length; simMatrixRow++)
            {
                for (int simMatrixCol = 0; simMatrixCol < simMatrix[0].length; simMatrixCol++)
                {
                    simMatrix[simMatrixRow][simMatrixCol] = Math.exp(-distMatrix[simMatrixRow][simMatrixCol] / (2 * Math.pow(256, 2)));
                }
            }
            return simMatrix;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private static Double[][] euclidean(Double[][] dataA, Double[][] dataB) throws Exception
    {
        int rA = dataA.length;
        int rB = dataB.length;
        int cA = dataA[0].length;
        int cB = dataB[0].length;
        //
        Double[][] distEu = new Double[cA][cB];
        //
        
        if (rA != rB)
        {
            throw new Exception("Matrix A and B have to have the same number of rows");
        }
        
        //distEu[1][2]
        //    SEGUNDA coluna de A
        //        A[0][1],A[1][1],A[2][1]
        //    Menos TERCEIRA coluna de B
        //        B[0][2],B[1][2],B[2][2]
        //    Ao quadrado
        //    Somado
        for (int distEuRow = 0; distEuRow < cA; distEuRow++)
        {
            for (int distEuCol = 0; distEuCol < cB; distEuCol++)
            {
                distEu[distEuRow][distEuCol] = (double) 0;
                
                for (int aRow = 0; aRow < rA; aRow++)
                {
                    double valorLinha = dataA[aRow][distEuRow] - dataB[aRow][distEuCol];
                    valorLinha = Math.pow(valorLinha, 2);
                    distEu[distEuRow][distEuCol] += valorLinha;
                }
            }
        }
        return distEu;
    }
    
    private static double getAverageNeedlemanWunschWithoutScore(Double[][] simMatrix)
    {
        double similarity = 0.0;
        Double sum = 0.0;
        Integer nroPeaks = 0;
        int smRow = 0;
        int smCol = 0;
        int smRowLen = simMatrix.length;
        int smColLen = simMatrix[0].length;
        
        while ((smRow < (smRowLen - 1)) && (smCol < (smColLen - 1)))
        {
            //            Em S[][] verificar quem � maior:
            //            S[+1][]     ABAIXO
            //            S[][+1]     DIREITA
            //            S[+1][+1]    DIAGONAL
            double diagonal = 0;
            double down = 0;
            double right = 0;
            
            if ((smRow + 1) < smRowLen)
            {
                down = simMatrix[smRow + 1][smCol];
            }
            if ((smCol + 1) < (smColLen))
            {
            	right = simMatrix[smRow][smCol + 1];
            }
            if ( ((smRow + 1) < smRowLen) && ((smCol + 1) < (smColLen)) )
            {
                diagonal = simMatrix[smRow + 1][smCol + 1];
            }
            
            ////
            if ((diagonal >= down) && (diagonal >= right))
            {
                sum += diagonal;
                nroPeaks++;
                smRow++;
                smCol++;
            }
            else if ((down >= diagonal) && (down >= right))
            {
                sum += down;
                nroPeaks++;
                smRow++;
            }
            else
            {
                sum += right;
                nroPeaks++;
                smCol++;
            }
        }
        
        if (nroPeaks > 0)
        {
            similarity = sum / nroPeaks;
        }
        return similarity;
    }
}
