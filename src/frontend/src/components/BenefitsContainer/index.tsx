// Importando as bibliotecas e arquivos necessários
import "./styles.scss"
import React from "react"
import vivoIcon from "../../assets/vivo-icon.svg"
import benefitsIcon from "../../assets/benefits-arrow.svg"

interface BenefitsContainerProps {
    clientType: string;
    benefitsLink: string;

}

// Componente de container de benefícios
const BenefitsContainer: React.FC<BenefitsContainerProps> = ({clientType, benefitsLink}) => {
    return(
        <div className="benefits-container">
            <div className="icon-text">

            <div className="icon-container">
                <img src={vivoIcon} alt="Vivo Icon" />
            </div>
            <div className="text-container">
                <h2>{clientType}</h2>
                <h3 className="know-benefits">Conheça seus benefícios</h3>
            </div>
            </div>
            <div className="benefits-container-icon">
            <a href={benefitsLink}><img src={benefitsIcon} alt="Benefits Icon"></img></a>
            </div>
        </div>

    )
    
}

export default BenefitsContainer;