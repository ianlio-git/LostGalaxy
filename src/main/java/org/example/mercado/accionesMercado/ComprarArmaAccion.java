package org.example.mercado.accionesMercado;
import org.example.Jugador;
import org.example.partesDeLaNave.Arma;
import org.example.partesDeLaNave.Armas.*;

import java.util.List;
import java.util.Scanner;

public class ComprarArmaAccion implements AccionMercado {

    @Override
    public void realizar(Jugador jugador) {

        System.out.println("Bienvenido a la tienda de armas");
        System.out.println("Ingrese el numero de arma deseada:");
        System.out.println("1. CAÑON_DE_IONES" );
        System.out.println("2. LASER_DE_FUSION");
        System.out.println("3. MISIL_DE_ANTIMATERIA ");
        System.out.println("4. CAÑON_DE_PARTICULAS");
        System.out.println("5. CAÑON_GAUSS");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el numero de arma a comprar:");
        int tipoDeArma = sc.nextInt();
        switch (tipoDeArma) {
            case 1:
                Arma cañonDeIones = new CañonDeIones();
                validarCoins(cañonDeIones,jugador);
                break;
            case 2:
                Arma laserDeFusion = new LaserDeFusion();
                validarCoins(laserDeFusion,jugador);
                break;
            case 3:
                Arma misilDeAntiMateria = new MisilDeAntiMateria();
                validarCoins(misilDeAntiMateria,jugador);
                break;
            case 4:
                Arma cañonDeParticulas = new CañonDeParticulas();
                validarCoins(cañonDeParticulas,jugador);
                break;
            case 5:
                Arma cañonGauss = new CañonGauss();
                validarCoins(cañonGauss,jugador);
                break;
            default:
                throw new IllegalArgumentException("Numero de arma no valida.");
            }

    }

    private void validarCoins(Arma tipoDeArma, Jugador jugador){

        if(jugador.getUadeCoins()> tipoDeArma.getPrecio()) {
            if(jugador.getNave().getArma()!=null){
                jugador.agregarUadeCoins(jugador.getNave().getArma().getPrecio());
            }
            jugador.getNave().cambiarArma(tipoDeArma);
            jugador.quitarUadeCoins(tipoDeArma.getPrecio());
            System.out.println("Usted ha comprado un:"+tipoDeArma.getTipoDeArma());
        }
        else{
            System.out.println("No tienes suficientes uade coins.");
        }
    }

}




