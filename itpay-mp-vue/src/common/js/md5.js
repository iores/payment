import crypto from 'crypto'

export default {
    getMd5: function (str) {
        let hash = crypto.createHash('md5');
        return hash.update(str).digest('hex');
    }
}