public class mergeSort{
    int[] fusion (int[] L1, int tailleL1, int[] L2, int tailleL2){

        if (L1 == null){
        return(L2);
        }

        if (L2 == null){
        return(L1);
        }

        int l1 = L1[0];
        int l2 = L2[0];

        if(l1 < l2){
        int[] L1Suite = new int[tailleL1 - 1];
        for (int i = 1 ; i < tailleL1; i++){
        L1Suite[i - 1] =   L1[i];
        }
        int[] LFusion = fusion(L1Suite, tailleL1 - 1, L2, tailleL2);
        int[] LTot = new int[tailleL1 + tailleL2];
        LTot[0]  = l1;
        for (int i = 1 ; i < tailleL1 + tailleL2; i++){
        LTot[i - 1] =   LFusion[i];
        }
        return(LTot);
        }
        else{
        int[] L2Suite = new int[tailleL2 - 1];
        for (int i = 1 ; i < tailleL2; i++){
        L2Suite[i - 1] =   L2[i];
        }
        int[] LFusion = fusion(L1, tailleL1, L2Suite, tailleL2 - 1);
        int[] LTot = new int[tailleL1 + tailleL2];
        LTot[0]  = l2;
        for (int i = 1 ; i < tailleL1 + tailleL2; i++){
        LTot[i - 1] =   LFusion[i];
        }
        return(LTot);
        }
        }

        int[] triFusion(int[] L, int longueur) {
            if (longueur < 1) {
                return (L);
            } else {
                int n1 = longueur / 2;
                int n2 = longueur - n1;
                int[] L1 = new int[n1];
                int[] L2 = new int[n2];
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