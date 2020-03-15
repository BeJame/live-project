import axios from './axios'

class Api {
    static checkCanAppointment() {
        return axios.get('/can_appointment')
    }

    // static checkPhone(phone) {
    //     return axios.get('/check_phone', {
    //         params: {
    //             phone
    //         }
    //     })
    // }
    
    // static checkIdNumber(idNumber) {
    //     return axios.get('/check_phone', {
    //         params: {
    //             idNumber
    //         }
    //     })
    // }

    static makeAppointment(data) {
        return axios.post('/appointment/create', data)
    }

    static getAppointmentResult(serialNumber) {
        return axios.get('/appointment', {
            params: {
                serialNumber
            }
        })
    }

    static TEST_start() {
        return axios.get('/test/start')
    }
    
    static TEST_end() {
        return axios.get('/test/end')
    }
}

export default Api
