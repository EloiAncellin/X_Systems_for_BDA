public class mergeSort{
    float[] fusion (float[] L1, int tailleL1, float[] L2, int tailleL2){

        if (L1 == null){
        return(L2);
        }

        if (L2 == null){
        return(L1);
        }

        float l1 = L1[0];
        float l2 = L2[0];

        if(l1 < l2){
        float[] L1Suite = new float[tailleL1 - 1];
        for (int i = 1 ; i < tailleL1; i++){
        L1Suite[i - 1] =   L1[i];
        }
        float[] LFusion = fusion(L1Suite, tailleL1 - 1, L2, tailleL2);
        float[] LTot = new float[tailleL1 + tailleL2];
        LTot[0]  = l1;
        for (int i = 1 ; i < tailleL1 + tailleL2; i++){
        LTot[i - 1] =   LFusion[i];
        }
        return(LTot);
        }
        else{
        float[] L2Suite = new float[tailleL2 - 1];
        for (int i = 1 ; i < tailleL2; i++){
        L2Suite[i - 1] =   L2[i];
        }
        float[] LFusion = fusion(L1, tailleL1, L2Suite, tailleL2 - 1);
        float[] LTot = new float[tailleL1 + tailleL2];
        LTot[0]  = l2;
        for (int i = 1 ; i < tailleL1 + tailleL2; i++){
        LTot[i - 1] =   LFusion[i];
        }
        return(LTot);
        }
        }

        float[] triFusion(float[] L, int longueur) {
            if (longueur < 1) {
                return (L);
            } else {
                int n1 = longueur / 2;
                int n2 = longueur - n1;
                float[] L1 = new float[n1];
                float[] L2 = new float[n2];
                for (int i = 0; i < longueur; i++) {
                    if (i < n1) {
                        L1[i] = L[i];
                    } else {
                        L2[i] = L[i];
                    }
                }
                return (fusion(L1, n1, L2, n2));
            }
        }
}