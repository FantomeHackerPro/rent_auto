package fr.ece.rentauto.controllers.web;

import fr.ece.rentauto.controllers.dtos.BookingResponseDTO;
import fr.ece.rentauto.controllers.dtos.CustomerResponseDTO;
import fr.ece.rentauto.domaines.entities.CustomUserDetails;
import fr.ece.rentauto.domaines.enumerations.*;
import fr.ece.rentauto.domaines.mappers.*;
import fr.ece.rentauto.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class WebViewController {

    private final BookingService bookingService;
    private AgencyService agencyService;
    private VehicleService vehicleService;
    private EquipmentService equipmentService;
    private CustomerService customerService;
    private StaffService staffService;
    private UserService userService;
    private PaymentService paymentService;

    @GetMapping("/login")
    public String login (Model model) {
        model.addAttribute("pageTitle", "Authentification - Rent Auto");
        return "authentication/login";
    }

    @GetMapping("/register")
    public String register (Model model) {
        model.addAttribute("pageTitle", "Création de compte - Rent Auto");
        return "authentication/register";
    }

    @GetMapping("/update-password")
    public String updatePassword (Model model) {
        model.addAttribute("pageTitle", "Création de compte - Rent Auto");
        return "authentication/update-password";
    }

    @GetMapping("agency")
    public String agency (Model model) {
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
        model.addAttribute("vehicleModal", "agency/vehicle");
        model.addAttribute("vehicleListTitle", "Liste des vehicles");
        model.addAttribute("staffModal", "agency/staff");
        model.addAttribute("staffListTitle", "Liste du personnel");

        return "layouts/main";
    }

    @GetMapping("/booking")
    public String booking (Model model) {
        model.addAttribute("pageTitle", "Gestion des réservations - Rent Car");
        model.addAttribute("sectionTitle", "Réservations");
        model.addAttribute("data", BookingMapper.mapToBookingResponseList(bookingService.getBookings()));
        model.addAttribute("vehicles", VehicleMapper.mapToVehicleResponseList(vehicleService.getVehicles(VehicleStatus.AVAILABLE)));
        model.addAttribute("customers", CustomerMapper.mapToCustomerResponseList(customerService.getCustomers()));
        model.addAttribute("paymentModes", PaymentMode.values());
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "booking/index");
        model.addAttribute("listTitle", "Liste des réservations");
        model.addAttribute("addModal", "booking/add");
        model.addAttribute("addModalTitle", "Enregistrement d'une réservation");
        model.addAttribute("updateModal", "booking/update");
        model.addAttribute("updateModalTitle", "Edition d'une réservation");
        model.addAttribute("paymentListTitle", "Liste des paiement");
        model.addAttribute("paymentModal", "booking/payment");
        model.addAttribute("paymentModalTitle", "Paiements");
        model.addAttribute("equipmentModal", "booking/equipment");
        model.addAttribute("equipmentListTitle", "Liste des équipements");

        return "layouts/main";
    }

    @GetMapping("/customer/booking")
    public String bookingCustomer (@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        CustomerResponseDTO customer = (userDetails != null) ? userDetails.getCustomer() : null;
        List<BookingResponseDTO> bookings = customer != null ? customer.getBookings() : new ArrayList<>();
        model.addAttribute("pageTitle", "Gestion des réservations - Rent Car");
        model.addAttribute("sectionTitle", "Réservations");
        model.addAttribute("data", bookings);
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "booking/index");
        model.addAttribute("listTitle", "Liste des réservations");
        model.addAttribute("addModal", "booking/add");
        model.addAttribute("addModalTitle", "Enregistrement d'une réservation");
        model.addAttribute("updateModal", "booking/update");
        model.addAttribute("updateModalTitle", "Edition d'une réservation");
        model.addAttribute("paymentListTitle", "Liste des paiement");
        model.addAttribute("paymentModal", "booking/payment");
        model.addAttribute("paymentModalTitle", "Paiements");
        model.addAttribute("equipmentModal", "booking/equipment");
        model.addAttribute("equipmentListTitle", "Liste des équipements");
        if (userDetails != null && userDetails.getCustomer() != null) {
            model.addAttribute("customer", userDetails.getCustomer());
        }

        return "layouts/main";
    }

    @GetMapping("/customer")
    public String customer (Model model) {
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
        model.addAttribute("bookingModal", "customer/booking");
        model.addAttribute("bookingListTitle", "Liste des reservations");

        return "layouts/main";
    }

    @GetMapping("/")
    public String dashboard(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        if (hasRole(authentication, UserRole.ADMIN)) {
            return "redirect:/admin/dashboard";
        }
        if (hasRole(authentication, UserRole.MANAGER)) {
            return "redirect:/manager/dashboard";
        }
        if (hasRole(authentication, UserRole.AGENT)) {
            return "redirect:/staff/dashboard";
        }

        return "redirect:/home";
    }

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin (Model model) {
        model.addAttribute("pageTitle", "Tableau de bord - Rent Auto");
        model.addAttribute("sectionTitle", "");
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "dashboard/admin/index");

        return "layouts/main";
    }

    @GetMapping("/manager/dashboard")
    public String dashboardManager (Model model) {
        model.addAttribute("pageTitle", "Tableau de bord - Rent Auto");
        model.addAttribute("sectionTitle", "");
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "dashboard/manager/index");

        return "layouts/main";
    }

    @GetMapping("/staff/dashboard")
    public String dashboardStaff (Model model) {
        model.addAttribute("pageTitle", "Tableau de bord - Rent Auto");
        model.addAttribute("sectionTitle", "");
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "dashboard/staff/index");

        return "layouts/main";
    }

    @GetMapping("/home")
    public String home (Model model) {
        model.addAttribute("pageTitle", "Acceuil - Rent Auto");
        model.addAttribute("sectionTitle", "Réservation des véhicules");
        model.addAttribute("vehicles", VehicleMapper.mapToVehicleResponseList(this.vehicleService.getVehicles(VehicleStatus.AVAILABLE)));
        model.addAttribute("sectionGroupTitle", "");
        model.addAttribute("content", "home/index");
        model.addAttribute("addModal", "booking/form");
        model.addAttribute("addModalTitle", "Enregistrement d'une réservation");
        model.addAttribute("paymentModes", PaymentMode.values());

        return "layouts/main";
    }

    @GetMapping("/equipment")
    public String equipment (Model model) {
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
    public String payment (Model model) {
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
    public String staff (Model model) {
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
    public String user (Model model) {
        model.addAttribute("pageTitle", "Gestion des utilisateurs - Rent Auto");
        model.addAttribute("sectionTitle", "Utilisateurs");
        model.addAttribute("data", UserMapper.mapToUserResponseList(this.userService.getUsers()));
        model.addAttribute("staffs", StaffMapper.mapToStaffResponseList(this.staffService.getAllStaff()));
        model.addAttribute("customers", CustomerMapper.mapToCustomerResponseList(this.customerService.getCustomers()));
        model.addAttribute("userTypes", UserRole.values());
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
    public String vehicle (Model model) {
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
        model.addAttribute("maintenanceListTitle", "Liste des maintenances");
        model.addAttribute("maintenanceModal", "vehicle/maintenance");
        model.addAttribute("maintenanceModalTitle", "Maintenances");
        model.addAttribute("equipmentModal", "vehicle/equipment");
        model.addAttribute("equipmentListTitle", "Liste des équipements");

        return "layouts/main";
    }

    @GetMapping("/redirect")
    public String redirectByRole(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        boolean isAdmin = hasRole(authentication, UserRole.ADMIN);
        if (isAdmin) {
            return "redirect:/admin/dashboard";
        }

        boolean isManager = hasRole(authentication, UserRole.MANAGER);
        if (isManager) {
            return "redirect:/manager/dashboard";
        }

        boolean isAgent = hasRole(authentication, UserRole.AGENT);
        if (isAgent) {
            return "redirect:/staff/dashboard";
        }

        return "redirect:/home";
    }

    private boolean hasRole(Authentication authentication, UserRole role) {
        String exact = role.name();
        String prefixed = "ROLE_" + role.name();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> exact.equals(authority) || prefixed.equals(authority));
    }
}
