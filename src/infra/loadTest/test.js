import http from 'k6/http'
import { sleep, check } from 'k6'

export const options = {
    vus: 2,
    duration: '1m'
}

export default function() {
    let res = http.get('http://projetonimbus.tech')
    check(res, {
        'is status 200': (r) => r.status === 200
    })
}
