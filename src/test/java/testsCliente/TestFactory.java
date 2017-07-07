package testsCliente;

import org.junit.Assert;
import org.junit.Test;

import cliente.EscuchaMensajes;
import mensajeriaComandos.ActualizarIntercambioCli;
import mensajeriaComandos.BatallaCli;
import mensajeriaComandos.Comando;
import mensajeriaComandos.FactoryComandoCliente;
import mensajeriaComandos.FactoryComandoEscuchaMensaje;
import mensajeriaComandos.FinalizarBatallaCli;
import mensajeriaComandos.InicioSesionCli;
import mensajeriaComandos.RegistroCli;

public class TestFactory {
    
    @Test
    public void testFactoryEscuchaMensajes(){
        Comando comando;
        FactoryComandoEscuchaMensaje f = new FactoryComandoEscuchaMensaje();
        comando = f.elegir(Comando.ACTUALIZARINTERCAMBIO, "cadena", new EscuchaMensajes());
        Comando prueba = new ActualizarIntercambioCli("cadena", new EscuchaMensajes());
        Assert.assertTrue(comando.getClass() == prueba.getClass());
        comando = f.elegir(Comando.BATALLA, "cadena", new EscuchaMensajes());
        prueba = new BatallaCli("cadena", new EscuchaMensajes());
        Assert.assertTrue(comando.getClass() == prueba.getClass());
        comando = f.elegir(Comando.FINALIZARBATALLA, "cadena", new EscuchaMensajes());
        prueba = new FinalizarBatallaCli("cadena", new EscuchaMensajes());
        Assert.assertTrue(comando.getClass() == prueba.getClass());
    }
    
    @Test
    public void testFactoryCliente(){
        Comando comando;
        FactoryComandoCliente f = new FactoryComandoCliente();
        comando = f.elegir(Comando.INICIOSESION, "cadena", null);
        Comando prueba = new InicioSesionCli("cadena", null);
        Assert.assertTrue(comando.getClass() == prueba.getClass());
        comando = f.elegir(Comando.REGISTRO, "cadena", null);
        prueba = new RegistroCli("cadena", null);
        Assert.assertTrue(comando.getClass() == prueba.getClass());
    }

}
