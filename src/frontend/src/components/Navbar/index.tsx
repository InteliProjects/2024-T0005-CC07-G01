import './styles.scss';

import logo from '../../assets/vivo-white-logo.svg';

// Barra de navegação
const Navbar= () => {
    return (
        <nav className="navbar">
            <div className='container-logo'>
                <a href='/home'><img className='logo' src={logo} alt="Vivo logo" /></a>
            </div>
        </nav>
    );
}

export default Navbar;