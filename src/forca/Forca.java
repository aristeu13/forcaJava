/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forca;

import java.util.Scanner;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author souar
 */
public class Forca {

    static public Scanner ler = new Scanner(System.in);
    static public Random randon = new Random();

    static public void play() {
        int i, vidas = 6, acertos = 0, acertosAnt = 0;
        String dicionario[] = {"Morcego","Gato","Cachorro","Macaco","Cavalo","Gaivota","Girafa","Elefante","Ornitorrinco","Panda"};
        
        int numero = randon.nextInt(9);

        String palavra_chave = dicionario[numero];

        String array_palavra[] = new String[palavra_chave.length()];
        for (i = 0; i < palavra_chave.length(); i++) {
            array_palavra[i] = "_";
        }

        String letrasUsadas[] = new String[30];
        for (i = 0; i < 30; i++) {
            letrasUsadas[i] = "0";
        }
        i = 1;

        while (true) {
            try {
                StringBuffer sb = new StringBuffer();
                int t;
                for (t = 0; t < palavra_chave.length(); t++) {
                    sb.append(array_palavra[t]).append(" ");
                }
                String palavra = sb.toString();

                String letra = JOptionPane.showInputDialog(null,
                        "Tentativas restantes: " + vidas + "\n palavra:  " + palavra, "FORCA - ANIMAIS",
                        JOptionPane.INFORMATION_MESSAGE);

                if (letra.equals("") || letra.length() > 1 || !Character.isLetter(letra.charAt(0))) {
                    throw new InvalidValueException();
                }

                int l, achou = 0;
                for (l = 0; !letrasUsadas[l].equals("0"); l++) {
                    if (letrasUsadas[l].equals(letra)) {
                        JOptionPane.showMessageDialog(null,
                                "Você já usou essa letra! ", "FORCA - ANIMAIS", JOptionPane.INFORMATION_MESSAGE);
                        achou = 1;
                        break;
                    }
                }

                if (achou == 1) {
                    continue;
                } else {
                    letrasUsadas[l] = letra;
                }

                acertosAnt = acertos;
                for (t = 0; t < palavra_chave.length(); t++) {
                    if (letra.equalsIgnoreCase(palavra_chave.substring(t, t + 1))) {
                        array_palavra[t] = palavra_chave.substring(t, t + 1);
                        acertos++;
                    }
                }

                if (acertosAnt != acertos) {
                    JOptionPane.showMessageDialog(null,
                            "ACERTOU!", "FORCA", JOptionPane.INFORMATION_MESSAGE);
                    if (acertos == palavra_chave.length()) {
                        JOptionPane.showMessageDialog(null,
                                "Parabéns você ganhou!\n palavra: " + palavra_chave, "FORCA - ANIMAIS", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "ERROU!", "FORCA", JOptionPane.INFORMATION_MESSAGE);
                    vidas--;
                    if (vidas == 0) {
                        JOptionPane.showMessageDialog(null,
                                "Não foi dessa vez! você perdeu!\n palavra: " + palavra_chave, "FORCA - ANIMAIS", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    continue;
                }

            } catch (InvalidValueException e) {
                JOptionPane.showMessageDialog(null,
                        "Valor invalido! Por favor informe uma unica letra", "FORCA - ANIMAIS", JOptionPane.WARNING_MESSAGE);
                continue;
            } catch (java.lang.NullPointerException e) {
                JOptionPane.showMessageDialog(null,
                        "Programa encerrado com sucesso!", "FORCA - ANIMAIS", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Forca jogar = new Forca("Maria");
        jogar.verificar();*/
        play();

    }

}
