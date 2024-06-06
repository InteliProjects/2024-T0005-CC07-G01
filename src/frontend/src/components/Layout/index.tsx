import React from 'react';
import Navbar from '../Navbar'; // Certifique-se de importar Navbar
import BottomNavbar from '../BottomNavbar'; // Certifique-se de importar BottomNavbar
import Sidebar from '../Sidebar';
import './styles.scss';

// Adicionado o prop showNavbar
interface LayoutProps {
  showSidebar?: boolean;
  showNavbar?: boolean;
  useBottomNavbar?: boolean;
  children?: React.ReactNode;
}

// Componente de layout
const Layout: React.FC<LayoutProps> = ({
  showSidebar,
  showNavbar,
  useBottomNavbar,
  children,
}) => {
  return (
    <div className="layout">
      {showNavbar && <Navbar />}
      <div className="side-home">
        {showSidebar && <Sidebar />}
        <main className="layout_main">{children}</main>
      </div>
      {useBottomNavbar && <BottomNavbar />}
    </div>
  );
};

export default Layout;