package org.example.GameMaster;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Exception.*;
import org.example.Views.GameBeginView;
import org.example.Enums.Dificultad;
import org.example.MapaEstelar.MapaEstelar;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Nave.TiposDeNaves.NaveAegis;
import org.example.Nave.TiposDeNaves.NavePhantom;
import org.example.Nave.TiposDeNaves.NaveSwift;
import org.example.Enums.TipoDeNave;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example.Nave.TiposDeNaves.NaveTitan;
import org.example.Views.MessageView;

public class Juego {
    private static int turno = 0;
    private boolean turnoExtra =true;
    private static Juego instanciaJuego;
    private MapaEstelar mapaEstelar;
    private Jugador jugador;
    private String mensajeRecibido;

    private Juego() {
        this.mapaEstelar = MapaEstelar.getInstancia();
    }

    public static Juego getInstancia() {
        if (instanciaJuego == null) {
            instanciaJuego = new Juego();
        }
        return instanciaJuego;
    }
    public void iniciarJuego(String nombreDelJugador, double uadeCoinsJugador, TipoDeNave naveJugador, int cantidadSistemasEstelares, Dificultad dificultad) throws JuegoIniciadoException {
        if (jugador == null) {
            mapaEstelar.crearMapaEstelar(dificultad,cantidadSistemasEstelares);
            SistemaEstelar sistemaInicial = mapaEstelar.obtenerSistemaEstelar("SIST-0");
            Planeta planetaInicial = sistemaInicial.obtenerPlanetaNeutral();
            switch (naveJugador) {
                case NAVE_AEGIS:
                    NaveAegis naveAegis = new NaveAegis();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveAegis,planetaInicial,sistemaInicial);
                    break;
                case NAVE_SWIFT:
                    NaveSwift naveSwift = new NaveSwift();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveSwift,planetaInicial,sistemaInicial);
                    break;
                case NAVE_PHANTOM:
                    NavePhantom navePhantom = new NavePhantom();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, navePhantom,planetaInicial,sistemaInicial);
                    break;
                case NAVE_TITAN:
                    NaveTitan naveTitan = new NaveTitan();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveTitan,planetaInicial,sistemaInicial);
                    break;
            }
        } else {
            throw new JuegoIniciadoException("El juego ya fue iniciado");
        }
    }


    public void indicarRumboANuevoSistema(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, SistemaInvalidoException {
        pasarTurno();
        if(mapaEstelar.verificarExistenciaDeSistemaEstelar(codigoDeSistema)){
            if (!jugador.getSistemaActual().mostrarNombre().equals(codigoDeSistema)) {
                SistemaEstelar sistemaNuevo = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema);
                jugador.comenzarViajeANuevoSistema(sistemaNuevo);//pase los metodos que devolvían string a void
                if(sistemaNuevo.tieneCinturonAsteroides()) {
                    jugador.setPosicionEnElEspacio("En cinturon de asteroides");
                }else{
                    jugador.setPosicionEnElEspacio("En el espacio");
                }
            }
        }
        else {
            throw new SistemaInvalidoException("Ingrese un sistema valido");
        }
    }



    public void comprarArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(planeta.ingresarAlMercado().accionDeComprarArma(jugador, tipoDeArma)){
               // finDelJuego("Compraste el "+tipoDeArma+" con exito!");  hay que pasar esto a la view
            }
            else {
                throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
            }
        }
    }

    public void comprarCombustible(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException {
        Planeta planeta = irAlShopping();
        if (!jugador.getNave().getTanque().tanqueLleno()) {
            if (planeta.ingresarAlMercado().accionDeComprarCombustible(jugador, cantidad)) {
                //finDelJuego("Compraste combustible con exito!");hay que pasar esto a la view
            } else {
                throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
            }
        }
    }

    public void recargarEscudo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException {
        Planeta planeta = irAlShopping();
        if(!jugador.getNave().getEscudo().escudoLleno()){
            if(planeta!=null){
                if(planeta.ingresarAlMercado().accionDeRecargarEscudo(jugador,cantidad)){
                    // finDelJuego("Recargaste escudo con exito!"); hay que pasar esto a la view
                }else{
                    throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
                }
            }
        }else{
            // finDelJuego("Escudos al maximo");
        }
    }



    public void venderArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, ArmaNoEncontradaException, PlanetaNoEncontradoException {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(planeta.ingresarAlMercado().accionDeVenderArma(jugador,tipoDeArma)){
                // finDelJuego("Vendiste el arma con exito!"); hay que pasar esto a la view
            }else{
                throw new ArmaNoEncontradaException("No posees el arma que quieres vender.");
            }
        }
    }
    public void comprarEscudoMaximo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, UadeCoinsInsuficientesException, PlanetaNoEncontradoException {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(planeta.ingresarAlMercado().accionDeComprarEscudoMaximo(jugador,cantidad)){
                // finDelJuego("Compraste escudo con exito"); hay que pasar esto a la view
            }else{
                throw new UadeCoinsInsuficientesException("No tienes suficientes uade coins!");
            }
        }
    }

    private Planeta irAlShopping() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaNeutral();
        System.out.println(planeta.getCodigoDePlaneta());
        jugador.setPlanetaActual(planeta);
        jugador.setPosicionEnElEspacio(planeta.getCodigoDePlaneta());
        System.out.println(planeta.getCodigoDePlaneta());
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            System.out.println(planeta.getCodigoDePlaneta());
            return planeta;
        }else {//si no puedo visitar un planeta neutral quiere decir que perdi
           // String mensaje = "No puedo visitar el planeta neutral, la nave no tiene combustible suficiente para esta accion.";
            throw new CombustibleInsuficienteException("Te quedaste sin combustible");
        }
    }

    public Jugador realizarAccionDeReparacion() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaAliado();

        if(planeta==null){
            throw new PlanetaNoEncontradoException("No se pudo encontrar el planeta.");
        }else{
            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                planeta.repararNaveAliada(jugador);
                // finDelJuego(String.format("¡Someteremos tu nave a reparación! \n ... \n ... \n ... \n ¡Hemos reparado tu nave con éxito! Ahora tiene "+jugador.getNave().getVida()+ " de vida y "+jugador.getNave().getEscudo().cantidadEscudoActual()+" de escudo! "));
                // hay que ver como pasar esto a la view

            } else {
                throw new CombustibleInsuficienteException("Te quedaste sin combustible");
            }
        }

        return jugador;

    }

    public void realizarAccionDeInformacion() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaAliado();
        if(planeta==null){
            throw new PlanetaNoEncontradoException("No se pudo encontrar el planeta.");
        }else{
            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                System.out.println(planeta.obtenerInformacion(jugador));
               // finDelJuego(planeta.obtenerInformacion(jugador)); que era esto?
            }
            else {
                throw new CombustibleInsuficienteException("Te quedaste sin combustible");
            }
        }

    }

    public void atacarPlanetaHostil(String codigoDePlaneta) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides, PlanetaNoEncontradoException, NaveEnemigaDestuidaException, NaveEnemigaDestuidaException {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaHostil(codigoDePlaneta);
        double cantDeUadeCoinsInicial = jugador.getUadeCoins();
        if(planeta==null){
            throw new PlanetaNoEncontradoException("No se pudo encontrar el planeta.");
        }else {
            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                planeta.combate(this.jugador);
                double cantDeUadeCoinsFinales = jugador.getUadeCoins();
                jugador.getSistemaActual().quitarPlaneta(planeta);
                throw new NaveEnemigaDestuidaException("La nave enemiga fue destruida. ¡Muy bien!, Por desgracia no has encontrado el tesoro, sigue intentado! \n UadeCoins obtenidas: " + (cantDeUadeCoinsFinales - cantDeUadeCoinsInicial));
            } else {
                throw new CombustibleInsuficienteException("Te quedaste sin combustible");
            }
        }
    }


    public MessageView messageToView() {
        return new MessageView(mensajeRecibido);
    }

    private void pasarTurno(){
        if (jugador.getNave().soyNaveTipo() == TipoDeNave.NAVE_TITAN) {
            if (turnoExtra) {
                turnoExtra = false;
            } else {
                turnoExtra = true;
                ++this.turno;
            }
        }else{
            ++this.turno;
        }

    }

    public Jugador getJugador() {
        return jugador;
    }

    public GameBeginView toViewgameBegin(){
        return new GameBeginView(jugador.getNombre(), jugador.getUadeCoins(),jugador.getNave().soyNaveTipo(),mapaEstelar.getSistemasEstelares().size(),mapaEstelar.getDificultad(),turno);
    }

    public MapaEstelar getMapaEstelar() {
        return mapaEstelar;
    }

    public static int getTurno() {
        return turno;
    }
}