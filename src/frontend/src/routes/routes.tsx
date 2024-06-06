import { createBrowserRouter } from "react-router-dom";
import Signin from "../pages/Signin";
import Signup from "../pages/Signup";
import Profile from "../pages/Profile";
import Plan from "../pages/Plan";
import { Home } from "../pages/Home";
import NotAuth from "../pages/NotAuth";

export const routes = createBrowserRouter([
  {
    path: "/",
    element: <Signin />,
  },
  {
    path: "/cadastro",
    element: <Signup />,
  },
  {
    path: "/home",
    element: <Home />,
  },
  {
    path: "/perfil",
    element: <Profile />,
  },
  {
    path: "/plano",
    element: <Plan />,
  },
  {
    path: "/nao-autenticado",
    element: <NotAuth />,
  }
]);
