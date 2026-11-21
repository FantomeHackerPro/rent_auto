package fr.ece.rentauto.controllers.web;

import fr.ece.rentauto.controllers.dtos.AgencyRequestDTO;
import fr.ece.rentauto.domaines.enumerations.StaffRole;
import fr.ece.rentauto.domaines.enumerations.VehicleCategory;
import fr.ece.rentauto.domaines.mappers.*;
import fr.ece.rentauto.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@AllArgsConstructor
@Controller
public class WebViewController {

    private AgencyService agencyService;
    private VehicleService vehicleService;
    private EquipmentService equipmentService;
    private CustomerService customerService;
    private StaffService staffService;
    private UserService userService;
    private PaymentService paymentService;

    @GetMapping("/login")
    public String Login (Model model) {
        model.addAttribute("pageTitle", "Login - Rent Auto");
        return "/login";
    }

    @GetMapping("agency")
    public String Agency (Model model) {
        model.addAttribute("pageTitle", "Gestion des agences - Rent Auto");
        model.addAttribute("sectionTitle", "Agences");
        model.addAttribute("data", AgencyMapper.mapToAgencyResponseList(this.agencyService.getAgencies()));
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "agency/index");
        model.addAttribute("listTitle", "Liste des agences");
        model.addAttribute("addModal", "agency/add");
        model.addAttribute("addModalTitle", "Enregistrement d'une agence");
        model.addAttribute("updateModal", "agency/update");
        model.addAttribute("updateModalTitle", "Edition des agences");
        model.addAttribute("request", new AgencyRequestDTO());

        return "layouts/main";
    }

    @GetMapping("/booking")
    public String Booking (Model model) {
        model.addAttribute("pageTitle", "Gestion des reservations - Rent Car");
        model.addAttribute("sectionTitle", "Reservations");
        model.addAttribute("data", new ArrayList<>());
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "booking/index");
        model.addAttribute("listTitle", "Liste des reservations");
        model.addAttribute("addModal", "booking/add");
        model.addAttribute("addModalTitle", "Enregistrement d'une reservation");
        model.addAttribute("updateModal", "booking/update");
        model.addAttribute("updateModalTitle", "Edition d'une reservation");

        return "layouts/main";
    }

    @GetMapping("/customer")
    public String Customer (Model model) {
        model.addAttribute("pageTitle", "Gestion des clients - Rent Car");
        model.addAttribute("sectionTitle", "Clients");
        model.addAttribute("data", CustomerMapper.mapToCustomerResponseList(customerService.getCustomers()));
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "customer/index");
        model.addAttribute("listTitle", "Liste des clients");
        model.addAttribute("addModal", "customer/add");
        model.addAttribute("addModalTitle", "Enregistrement d'un client");
        model.addAttribute("updateModal", "customer/update");
        model.addAttribute("updateModalTitle", "Edition des clients");

        return "layouts/main";
    }

    @GetMapping("/dashboard")
    public String Dashboard (Model model) {
        model.addAttribute("pageTitle", "Tableau de bord - Rent Auto");
        model.addAttribute("sectionTitle", "");
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "dashboard/index");

        return "layouts/main";
    }

    @GetMapping("/equipment")
    public String Equipment (Model model) {
        model.addAttribute("pageTitle", "Gestion des équipements - Rent Auto");
        model.addAttribute("sectionTitle", "Equipements");
        model.addAttribute("data", EquipmentMapper.mapToEquipmentToResponseList(this.equipmentService.getEquipments()));
        model.addAttribute("roles", StaffRole.values());
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "equipment/index");
        model.addAttribute("listTitle", "Liste des équipements");
        model.addAttribute("addModal", "equipment/add");
        model.addAttribute("addModalTitle", "Enregistrement d'un équipement");
        model.addAttribute("updateModal", "equipment/update");
        model.addAttribute("updateModalTitle", "Edition d'un équipement");

        return "layouts/main";
    }

    @GetMapping("/payment")
    public String Payment (Model model) {
        model.addAttribute("pageTitle", "Gestion des paiements - Rent Auto");
        model.addAttribute("sectionTitle", "Paiements");
        model.addAttribute("data", PaymentMapper.mapToPaymentResponseList(this.paymentService.getPayments()));
        model.addAttribute("roles", StaffRole.values());
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "payment/index");
        model.addAttribute("listTitle", "Liste des paiements");
        model.addAttribute("addModal", "payment/add");
        model.addAttribute("addModalTitle", "Enregistrement d'un paiement");
        model.addAttribute("updateModal", "payment/update");
        model.addAttribute("updateModalTitle", "Edition d'un paiement");

        return "layouts/main";
    }

    @GetMapping("/staff")
    public String Staff (Model model) {
        model.addAttribute("pageTitle", "Gestion du personnel - Rent Auto");
        model.addAttribute("sectionTitle", "Personnel");
        model.addAttribute("data", StaffMapper.mapToStaffResponseList(this.staffService.getAllStaff()));
        model.addAttribute("agencies", AgencyMapper.mapToAgencyResponseList(this.agencyService.getAgencies()));
        model.addAttribute("roles", StaffRole.values());
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "staff/index");
        model.addAttribute("listTitle", "Liste du personnel");
        model.addAttribute("addModal", "staff/add");
        model.addAttribute("addModalTitle", "Enregistrement d'un personnel");
        model.addAttribute("updateModal", "staff/update");
        model.addAttribute("updateModalTitle", "Edition d'un personnel");

        return "layouts/main";
    }

    @GetMapping("/user")
    public String User (Model model) {
        model.addAttribute("pageTitle", "Gestion des utilisateurs - Rent Auto");
        model.addAttribute("sectionTitle", "Utilisateurs");
        model.addAttribute("data", UserMapper.mapToUserResponseList(this.userService.getUsers()));
        model.addAttribute("roles", StaffRole.values());
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "user/index");
        model.addAttribute("listTitle", "Liste des utilisateurs");
        model.addAttribute("addModal", "user/add");
        model.addAttribute("addModalTitle", "Enregistrement d'un utilisateur");
        model.addAttribute("updateModal", "user/update");
        model.addAttribute("updateModalTitle", "Edition d'un utilisateur");

        return "layouts/main";
    }

    @GetMapping("/vehicle")
    public String Vehicle (Model model) {
        model.addAttribute("pageTitle", "Gestion des véhicules - Rent Auto");
        model.addAttribute("sectionTitle", "Véhicules");
        model.addAttribute("data", VehicleMapper.mapToVehicleResponseList(this.vehicleService.getVehicles()));
        model.addAttribute("agencies", AgencyMapper.mapToAgencyResponseList(this.agencyService.getAgencies()));
        model.addAttribute("equipments", EquipmentMapper.mapToEquipmentToResponseList(this.equipmentService.getEquipments()));
        model.addAttribute("vehicleCategories", VehicleCategory.values());
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "vehicle/index");
        model.addAttribute("listTitle", "Liste des véhicules");
        model.addAttribute("addModal", "vehicle/add");
        model.addAttribute("addModalTitle", "Enregistrement d'un véhicule");
        model.addAttribute("updateModal", "vehicle/update");
        model.addAttribute("updateModalTitle", "Edition d'un véhicule");
        model.addAttribute("addInMaintenanceModal", "vehicle/add-in-maintenance");
        model.addAttribute("addInMaintenanceModalTitle", "Mise en maintenance");

        return "layouts/main";
    }
}
